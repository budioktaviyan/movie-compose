package id.android.movie.ext

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.WithConstraints
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import coil.Coil
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import coil.size.Scale
import coil.target.Target
import id.android.movie.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CoilImage(
    model: Any,
    modifier: Modifier = Modifier,
    customize: LoadRequestBuilder.() -> Unit = {}
) {
  WithConstraints(modifier) {
    var width = 256
    var height = 128

    if (width == -1) width = height
    if (height == -1) height = width

    val image = state { ImageAsset(width, height) }
    val context = ContextAmbient.current

    var animationJob: Job? = remember { null }

    onCommit(model) {
      val target = object : Target {
        override fun onStart(placeholder: Drawable?) {
          placeholder?.apply {

            if (height == 1) {
              val scaledHeight = intrinsicHeight * (width / intrinsicWidth)
              image.value = toBitmap(width, scaledHeight).asImageAsset()
            }

            if (width == 1) {
              val scaledWidth = intrinsicWidth * (height / intrinsicWidth)
              image.value = toBitmap(scaledWidth, height).asImageAsset()
            }
          }
        }

        override fun onSuccess(result: Drawable) {
          if (result is Animatable) {
            (result as Animatable).start()

            animationJob = GlobalScope.launch(Dispatchers.Default) {
              while (true) {
                val asset = result.toBitmap().asImageAsset()
                withContext(Dispatchers.Main) {
                  image.value = asset
                }
                delay(16)
              }
            }
          } else {
            image.value = result.toBitmap().asImageAsset()
          }
        }

        override fun onError(error: Drawable?) {
          error?.run {
            image.value = toBitmap().asImageAsset()
          }
        }
      }

      val request = LoadRequest.Builder(context)
          .data(model)
          .size(width, height)
          .scale(Scale.FILL)
          .apply { customize(this) }
          .target(target)

      val requestDisposable = Coil.imageLoader(context).execute(request.build())

      onDispose {
        image.value = ImageAsset(width, height)
        requestDisposable.dispose()
        animationJob?.cancel()
      }
    }

    Image(modifier = modifier, asset = image.value)
  }
}

@Preview
@Composable
fun ProfilePreview() {
  Surface {
    CoilImage(R.drawable.ic_launcher_foreground)
  }
}
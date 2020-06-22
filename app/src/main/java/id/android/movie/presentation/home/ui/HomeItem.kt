package id.android.movie.presentation.home.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.ProvideEmphasis
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import id.android.movie.domain.home.HomeEntity.Data
import id.android.movie.presentation.ui.UiState

@Composable
fun HomeItem(data: Data) {
  Column {
    MovieImage(
        data = data,
        modifier = Modifier.fillMaxSize().padding(top = 8.dp)
    )
    MovieTitle(
        data = data,
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
    )
    MovieDescription(
        data = data,
        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
    )
  }
}

@Composable
fun MovieImage(data: Data, modifier: Modifier) {
  val image = ImageLoad(data.image)

  if (image is UiState.Success<Bitmap>) {
    Image(
        asset = image.data.asImageAsset(),
        modifier = modifier.preferredSize(256.dp, 128.dp),
        contentScale = ContentScale.FillWidth
    )
  } else {
    HomeItemLoading()
  }
}

@Composable
fun MovieTitle(data: Data, modifier: Modifier) {
  Row(modifier) {
    ProvideEmphasis(EmphasisAmbient.current.high) {
      Text(
          text = data.title,
          fontSize = 14.sp
      )
    }
  }
}

@Composable
fun MovieDescription(data: Data, modifier: Modifier) {
  Row(modifier) {
    ProvideEmphasis(EmphasisAmbient.current.medium) {
      Text(
          text = data.description,
          fontSize = 12.sp
      )
    }
  }
}

@Composable
private fun HomeItemLoading() {
  Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    CircularProgressIndicator(color = Color.Black)
  }
}

@Composable
private fun ImageLoad(url: String): UiState<Bitmap> {
  var bitmapState: UiState<Bitmap> by state<UiState<Bitmap>> { UiState.Loading }

  Glide.with(ContextAmbient.current)
      .asBitmap()
      .load(url)
      .override(256, 128)
      .into(object : CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
          bitmapState = UiState.Success(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
      })

  return bitmapState
}
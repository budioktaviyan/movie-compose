package id.android.movie.presentation.home.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.RowScope.weight
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import id.android.movie.domain.home.HomeEntity.Data
import id.android.movie.ext.CoilImage

@Composable
fun HomeItem(data: Data) {
  Column(Modifier.weight(1f)) {
    MovieImage(data = data)
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

@Preview("Home Item")
@Composable
fun HomeItemPreview() {
  val data = Data(
      title = "Untitled",
      description = "No Description",
      image = "untitled.png"
  )

  Surface {
    HomeItem(data)
  }
}

@Composable
fun MovieImage(data: Data) {
  CoilImage(
      model = data,
      modifier = Modifier.preferredSize(256.dp, 128.dp)
  )
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
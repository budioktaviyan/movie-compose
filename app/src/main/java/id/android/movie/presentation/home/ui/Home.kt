package id.android.movie.presentation.home.ui

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.layout.wrapContentSize
import androidx.ui.livedata.observeAsState
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import id.android.movie.domain.home.HomeEntity.Data
import id.android.movie.presentation.home.HomeView
import id.android.movie.presentation.home.HomeViewState.Failed
import id.android.movie.presentation.home.HomeViewState.Loading
import id.android.movie.presentation.home.HomeViewState.Success

@Composable
fun Home(view: HomeView) {
  Scaffold(
      topBar = {
        TopAppBar(
            title = { Text(text = "Movie") },
            backgroundColor = Color.White,
            elevation = 2.dp
        )
      },
      bodyContent = { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        val state by view.states.observeAsState(Loading)
        when (state) {
          is Loading -> HomeLoading()
          is Success -> {
            val entity = (state as Success).entity
            HomeBody(data = entity.data, modifier = modifier)
          }
          is Failed -> {
            val context = ContextAmbient.current
            val exception = (state as Failed).exception
            Toast.makeText(context, exception, Toast.LENGTH_SHORT).show()
          }
        }
      }
  )
}

@Composable
fun HomeBody(data: List<Data>, modifier: Modifier = Modifier) {
  VerticalScroller(modifier = modifier) {
    Column {
      data.map { item ->
        HomeItem(item)
        HomeDivider()
      }
    }
  }
}

@Composable
private fun HomeLoading() {
  Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    CircularProgressIndicator(color = Color.Black)
  }
}

@Composable
private fun HomeDivider() {
  Divider(
      modifier = Modifier.padding(horizontal = 8.dp),
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
  )
}
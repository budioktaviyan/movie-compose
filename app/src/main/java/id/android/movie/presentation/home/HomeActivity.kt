package id.android.movie.presentation.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import dagger.android.support.DaggerAppCompatActivity
import id.android.movie.presentation.home.ui.Home
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel: HomeView by lazy {
    ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Home(view = viewModel)
    }

    viewModel.discoverMovie()
  }
}
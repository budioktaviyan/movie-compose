package id.android.movie.presentation.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.android.movie.presentation.home.HomeViewState.Failed
import id.android.movie.presentation.home.HomeViewState.Loading
import id.android.movie.presentation.home.HomeViewState.Show
import id.android.movie.presentation.home.HomeViewState.Success
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel: HomeView by lazy {
    ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.states.observe(this, Observer(this::stateHandler))
    viewModel.discoverMovie()
  }

  private fun stateHandler(state: HomeViewState) {
    TODO("Not yet implemented!")
  }
}
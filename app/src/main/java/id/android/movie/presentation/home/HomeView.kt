package id.android.movie.presentation.home

import androidx.lifecycle.LiveData

interface HomeView {

  val states: LiveData<HomeViewState>

  fun discoverMovie()
}
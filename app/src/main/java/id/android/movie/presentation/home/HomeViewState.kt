package id.android.movie.presentation.home

import id.android.movie.domain.home.HomeEntity

sealed class HomeViewState {

  object Loading : HomeViewState()

  data class Success(val entity: HomeEntity) : HomeViewState()
  data class Failed(val exception: String) : HomeViewState()
}
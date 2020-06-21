package id.android.movie.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.android.movie.BuildConfig
import id.android.movie.domain.DefaultObserver
import id.android.movie.domain.home.HomeEntity
import id.android.movie.domain.home.HomeParam
import id.android.movie.domain.home.HomeUsecase

class HomeViewModel(private val usecase: HomeUsecase) : ViewModel(), HomeView {

  private val observer = MutableLiveData<HomeViewState>()
  override val states: LiveData<HomeViewState>
    get() = observer

  override fun discoverMovie() {
    observer.postValue(HomeViewState.Loading)

    val usecaseObserver = HomeUsecaseObserver()
    val parametes = HomeParam(key = BuildConfig.API_KEY)
    usecase.execute(usecaseObserver, parametes)
  }

  override fun onCleared() {
    super.onCleared()
    usecase.dispose()
  }

  inner class HomeUsecaseObserver : DefaultObserver<HomeEntity>() {

    override fun onSuccess(t: HomeEntity) {
      observer.postValue(HomeViewState.Show)
      observer.postValue(HomeViewState::Success.invoke(t))
    }

    override fun onError(e: Throwable?) {
      observer.postValue(HomeViewState.Show)
      observer.postValue(HomeViewState::Failed.invoke("$e"))
    }
  }
}
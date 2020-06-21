package id.android.movie.domain

import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class DefaultObserver<T> : DisposableSingleObserver<T>() {

  override fun onSuccess(t: T) {}
  override fun onError(e: Throwable?) {}
}
package id.android.movie.domain

import id.android.movie.domain.executor.PostExecutionThread
import id.android.movie.domain.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class Usecase<T, in Parameters>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

  private val compositeDisposable = CompositeDisposable()

  protected abstract fun buildUsecaseObservable(parameters: Parameters): Single<T>

  fun execute(observer: DefaultObserver<T>, parameters: Parameters) {
    buildUsecaseObservable(parameters)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.scheduler)
        .subscribeWith(observer)
        .addTo(compositeDisposable)
  }

  fun dispose() {
    compositeDisposable.clear()
  }
}
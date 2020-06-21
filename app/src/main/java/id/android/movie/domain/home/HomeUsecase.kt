package id.android.movie.domain.home

import id.android.movie.domain.Usecase
import id.android.movie.domain.executor.JobExecutor
import id.android.movie.domain.executor.MainThread
import io.reactivex.rxjava3.core.Single

class HomeUsecase(
    private val repository: HomeRepository,
    executor: JobExecutor,
    thread: MainThread
) : Usecase<HomeEntity, HomeParam>(executor, thread) {

  override fun buildUsecaseObservable(parameters: HomeParam): Single<HomeEntity> = repository.discoverMovie(parameters)
}
package id.android.movie.domain.executor

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {

  val scheduler: Scheduler
}

class MainThread : PostExecutionThread {

  override val scheduler: Scheduler
    get() = AndroidSchedulers.mainThread()
}
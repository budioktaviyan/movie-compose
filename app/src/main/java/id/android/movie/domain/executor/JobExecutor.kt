package id.android.movie.domain.executor

import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit.SECONDS

interface ThreadExecutor : Executor

class JobExecutor : ThreadExecutor {

  private val threadPoolExecutor = ThreadPoolExecutor(
      3,
      5,
      10,
      SECONDS,
      LinkedBlockingQueue(),
      JobThreadFactory()
  )

  override fun execute(command: Runnable?) {
    threadPoolExecutor.execute(command)
  }
}

class JobThreadFactory(private var counter: Int = 0) : ThreadFactory {

  override fun newThread(r: Runnable?): Thread {
    return Thread(r, "android_${counter.inc()}")
  }
}
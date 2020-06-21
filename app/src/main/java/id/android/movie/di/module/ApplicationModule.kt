package id.android.movie.di.module

import android.annotation.SuppressLint
import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import id.android.movie.MovieApp
import id.android.movie.domain.executor.JobExecutor
import id.android.movie.domain.executor.MainThread

@SuppressLint("ModuleCompanionObjects")
@Module
abstract class ApplicationModule {

  @Module
  companion object {

    @Provides
    fun providesJobExecutor() = JobExecutor()

    @Provides
    fun providesMainThread() = MainThread()
  }

  @Binds
  abstract fun application(app: MovieApp): Application
}
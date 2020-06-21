package id.android.movie

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import id.android.movie.di.DaggerApplicationComponent

class MovieApp : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.factory().create(this).apply {
      inject(this@MovieApp)
    }
  }
}
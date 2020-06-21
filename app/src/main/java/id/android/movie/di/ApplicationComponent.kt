package id.android.movie.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.android.movie.MovieApp
import id.android.movie.di.module.ActivityModule
import id.android.movie.di.module.ApplicationModule
import id.android.movie.di.module.NetworkModule
import id.android.movie.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ApplicationModule::class,
  ViewModelModule::class,
  NetworkModule::class,
  ActivityModule::class
])
interface ApplicationComponent : AndroidInjector<MovieApp> {

  @Component.Factory
  abstract class Factory : AndroidInjector.Factory<MovieApp>
}
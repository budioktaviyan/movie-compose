package id.android.movie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.android.movie.di.submodule.HomeModule
import id.android.movie.presentation.home.HomeActivity

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector(modules = [HomeModule::class])
  abstract fun contributeHomeActivity(): HomeActivity
}
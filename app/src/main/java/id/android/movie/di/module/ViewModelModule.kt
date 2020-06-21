package id.android.movie.di.module

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import id.android.movie.di.ViewModelFactory
import javax.inject.Provider

@SuppressLint("ModuleCompanionObjects")
@Module
abstract class ViewModelModule {

  @Module
  companion object {

    @Provides
    fun providesViewModelFactory(viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) = ViewModelFactory(viewModels)
  }

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
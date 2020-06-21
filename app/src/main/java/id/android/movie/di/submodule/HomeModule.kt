package id.android.movie.di.submodule

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import id.android.movie.data.home.HomeDatasource
import id.android.movie.data.home.HomeFactory
import id.android.movie.di.ViewModelKey
import id.android.movie.domain.executor.JobExecutor
import id.android.movie.domain.executor.MainThread
import id.android.movie.domain.home.HomeRepository
import id.android.movie.domain.home.HomeRepositoryImpl
import id.android.movie.domain.home.HomeUsecase
import id.android.movie.presentation.home.HomeViewModel
import retrofit2.Retrofit

@SuppressLint("ModuleCompanionObjects")
@Module
abstract class HomeModule {

  @Module
  companion object {

    @Provides
    fun providesDatasource(retrofit: Retrofit): HomeDatasource = retrofit.create(HomeDatasource::class.java)

    @Provides
    fun providesFactory(datasource: HomeDatasource) = HomeFactory(datasource)

    @Provides
    fun providesRepository(factory: HomeFactory) = HomeRepositoryImpl(factory)

    @Provides
    fun providesUsecase(
        repository: HomeRepository,
        executor: JobExecutor,
        thread: MainThread) = HomeUsecase(repository, executor, thread)

    @Provides
    fun providesViewModel(usecase: HomeUsecase) = HomeViewModel(usecase)
  }

  @Binds
  abstract fun bindRepository(repository: HomeRepositoryImpl): HomeRepository

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
package id.android.movie.domain.home

import id.android.movie.BuildConfig
import id.android.movie.data.home.HomeFactory
import id.android.movie.domain.home.HomeEntity.Data
import io.reactivex.rxjava3.core.Single

class HomeRepositoryImpl(private val factory: HomeFactory) : HomeRepository {

  override fun discoverMovie(parameters: HomeParam): Single<HomeEntity> =
      factory.discoverMovie(parameters).map { response ->
        HomeEntity(
            data = response.data.map { data ->
              Data(
                  title = data?.title ?: "Untitled",
                  description = data?.description ?: "No Description",
                  image = "${BuildConfig.IMAGE_URL}/${data?.image ?: "untitled.png"}"
              )
            }
        )
      }
}
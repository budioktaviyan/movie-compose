package id.android.movie.domain.home

import io.reactivex.rxjava3.core.Single

interface HomeRepository {

  fun discoverMovie(parameters: HomeParam): Single<HomeEntity>
}
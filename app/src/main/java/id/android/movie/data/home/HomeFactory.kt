package id.android.movie.data.home

import id.android.movie.domain.home.HomeParam

class HomeFactory(private val datasource: HomeDatasource) {

  fun discoverMovie(parameters: HomeParam) = datasource.discoverMovie(parameters.key)
}
package id.android.movie.data.home

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeDatasource {

  @GET("/3/discover/movie")
  fun discoverMovie(@Query("api_key") key: String): Single<HomeResponse>
}
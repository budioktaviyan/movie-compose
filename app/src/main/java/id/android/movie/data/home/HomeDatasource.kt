package id.android.movie.data.home

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface HomeDatasource {

  fun discoverMovie(@Query("api_key") key: String): Single<HomeResponse>
}
package id.android.movie.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import id.android.movie.BuildConfig
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun providesCache(application: Application) = Cache(application.cacheDir, 30.times(1024).times(1024))

  @Provides
  @Singleton
  fun providesConnectionPool() = ConnectionPool(15, 30.times(1000), SECONDS)

  @Provides
  @Singleton
  fun providesHttpInterceptor() = HttpLoggingInterceptor().apply { level = BODY }

  @Provides
  @Singleton
  fun providesHttpClient(
      cache: Cache,
      connectionPool: ConnectionPool,
      interceptor: HttpLoggingInterceptor) =
      OkHttpClient.Builder().apply {
        connectTimeout(30, SECONDS)
        readTimeout(15, SECONDS)
        writeTimeout(15, SECONDS)
        retryOnConnectionFailure(true)
        cache(cache)
        connectionPool(connectionPool)
        addInterceptor(interceptor)
      }.build()

  @Provides
  @Singleton
  fun providesHttpAdapter(client: OkHttpClient): Retrofit =
      Retrofit.Builder().apply {
        client(client)
        baseUrl(BuildConfig.BASE_URL)
        addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
        addConverterFactory(GsonConverterFactory.create())
      }.build()
}
package id.android.movie.domain.home

data class HomeEntity(val data: List<Data>) {

  data class Data(
      val title: String,
      val description: String,
      val image: String
  )
}
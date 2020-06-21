package id.android.movie.data.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("results")
    val data: List<Data?>
)

data class Data(
    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val description: String?,

    @SerializedName("poster_path")
    val image: String?
)
package com.cursokotlinjun.challengemarvelappinter.domain.model

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
   @SerializedName("dates") val dates: List<ComicData>
)

data class ComicData(
    @SerializedName("type")  val type: String,
   @SerializedName("date") val date: String
)

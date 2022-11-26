package com.cursokotlinjun.challengemarvelappinter.domain.model

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("start") val date: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
)

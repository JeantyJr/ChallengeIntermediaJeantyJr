package com.cursokotlinjun.challengemarvelappinter.domain.model

import com.google.gson.annotations.SerializedName

data class EventDato(
    @SerializedName("id") val id: Int,
    @SerializedName("title")val title: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("start")val date: String,
    var isVisible: Boolean = false
)

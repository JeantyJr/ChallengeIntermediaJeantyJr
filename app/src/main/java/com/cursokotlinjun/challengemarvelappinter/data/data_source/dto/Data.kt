package com.cursokotlinjun.challengemarvelappinter.data.data_source.dto

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("count") val count: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("results") val results: T,
    @SerializedName("total") val total: Int

)

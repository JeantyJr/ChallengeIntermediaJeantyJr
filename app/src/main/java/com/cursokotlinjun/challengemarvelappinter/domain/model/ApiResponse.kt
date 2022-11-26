package com.cursokotlinjun.challengemarvelappinter.domain.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: T,
    @SerializedName("etag") val etag: String,
    @SerializedName("status") val status: String
)

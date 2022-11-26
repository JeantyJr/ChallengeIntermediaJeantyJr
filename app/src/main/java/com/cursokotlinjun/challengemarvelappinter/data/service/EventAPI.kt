package com.cursokotlinjun.challengemarvelappinter.data.service

import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.ApiResponse
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.domain.model.Events
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EventAPI {

    @GET("events")
    suspend fun getAllEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("orderBy")orderBy: String,
        @Query("limit") limit: Int
    ): Response<ApiResponse<Data<List<Events>>>>

    @GET("events/{eventId}/comics")
    suspend fun getAllComicsEvents(
        @Path("eventId") eventId: Int,
        @QueryMap auth: HashMap<String, String>,
        @Query("limit") limit: Int
    ): Response<ApiResponse<Data<List<Comic>>>>
}
package com.cursokotlinjun.challengemarvelappinter.data.service


import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.ApiResponse
import com.cursokotlinjun.challengemarvelappinter.domain.model.Character
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface MarvelAPI {
    @GET("characters")
    suspend fun getAllCharacters(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset")offset:Int,
        @Query("limit")limit: Int
    ): Response<ApiResponse<Data<List<Character>>>>

    @GET("characters/{characterId}/comics")
        suspend fun getAllComics(
            @Path("characterId") characterId: Int,
            @QueryMap auth: HashMap<String, String>,
        //    @Query("offset") offset: Int,
            @Query("limit") limit: Int
    ): Response<ApiResponse<Data<List<Comic>>>>

}
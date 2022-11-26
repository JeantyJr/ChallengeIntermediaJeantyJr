package com.cursokotlinjun.challengemarvelappinter.data.data_source

import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.ApiResponse
import retrofit2.Response
import java.io.Serializable

abstract class MarvelDataSource {

     protected suspend fun <T> getResult(call: suspend () -> Response<ApiResponse<Data<T>>>): Ressource<T>{
         try{
             val response = call()
             if (response.isSuccessful){
                 val body = response.body()?.data?.results
                 if (body != null) return Ressource.success(body)
             }
             return Ressource.error("${response.code()}: ${response.message()}")
             } catch (e: Exception){
                 return Ressource.error(e.message?: "Error!")
             }

     }
}

data class Ressource<out T>(var status: Status, val data: T?, val message: String?): Serializable{
    enum class Status{
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{
        fun<T> success(data:T?): Ressource<T>{
            return Ressource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Ressource<T>{
            return Ressource(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): Ressource<T>{
            return Ressource(
                Status.LOADING,
                data,
                null
            )
        }

    }
}
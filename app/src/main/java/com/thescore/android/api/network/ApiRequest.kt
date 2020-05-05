package com.thescore.android.api.network

import retrofit2.Response
import java.lang.Exception

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

abstract class ApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Any {
        val response = call.invoke()
        return try {
            ApiResponse(response.body()!!)
        } catch (exception: Exception) {
            ApiResponse(exception.message.toString())
        }
    }
}
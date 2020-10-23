package com.tokopedia.durianmoney_covid_chatbot.core.base

import android.util.Log
import com.tokopedia.durianmoney_covid_chatbot.data.networks.Results
import retrofit2.Response

class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Results<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Results.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Results<T> {
        Log.e("error", message)
        return Results.error("Network call has failed for a following reason: $message")
    }
}
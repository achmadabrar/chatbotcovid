package com.tokopedia.durianmoney_covid_chatbot.data.networks

data class Results<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        EMPTY
    }

    companion object {
        fun <T> success(data: T): Results<T> {
            return Results(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Results<T> {
            return Results(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Results<T> {
            return Results(Status.LOADING, data, null)
        }

        fun <T> empty(data: T? = null): Results<T> {
            return Results(Status.EMPTY, data, null)
        }
    }
}
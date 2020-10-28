package com.tokopedia.durianmoney_covid_chatbot.external

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import com.tokopedia.durianmoney_covid_chatbot.data.networks.Results
import com.tokopedia.durianmoney_covid_chatbot.data.networks.Results.Companion.loading


/*
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Results<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Results<T>> =
    liveData(Dispatchers.IO) {
        emit(loading<T>())
        val source = databaseQuery.invoke().map { Results.success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Results.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Results.Status.ERROR) {
            emit(Results.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }*/

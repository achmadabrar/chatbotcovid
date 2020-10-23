package com.tokopedia.durianmoney_covid_chatbot.data.database

import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData

interface ChatDataStore {
    suspend fun getQuery(): MutableList<StateData>?
    suspend fun addAll(query: String, products: MutableList<StateData>?)
}
package com.tokopedia.durianmoney_covid_chatbot.data.models

data class UserQueryModel(
    val query: String,
    val isFromUser: Boolean,
    val botWorldResponse: WorldResponse?,
    val stateData: StateData?,
    val countryCode: String?,
    val date: String
)
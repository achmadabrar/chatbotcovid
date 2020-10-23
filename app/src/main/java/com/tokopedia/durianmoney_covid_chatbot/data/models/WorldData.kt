package com.tokopedia.durianmoney_covid_chatbot.data.models

import com.google.gson.annotations.SerializedName

data class WorldData (
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Long,
    @SerializedName("TotalDeaths")
    val totalDeaths: Long,
    @SerializedName("TotalRecovered")
    val totalRecovered: Long
)
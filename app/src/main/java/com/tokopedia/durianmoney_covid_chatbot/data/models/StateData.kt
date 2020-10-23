package com.tokopedia.durianmoney_covid_chatbot.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class StateData (
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Province")
    val province: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("CityCode")
    val cityCode: String,
    @SerializedName("Lat")
    val lat: String,
    @SerializedName("Lon")
    val lon: String,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Deaths")
    val deaths: String,
    @SerializedName("Recovered")
    val recovered: Int,
    @SerializedName("Active")
    val active: Int,
    @SerializedName("Date")
    val date: Date
)
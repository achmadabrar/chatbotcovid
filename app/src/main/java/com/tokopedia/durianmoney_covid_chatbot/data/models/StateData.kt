package com.tokopedia.durianmoney_covid_chatbot.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "state_data_table")
data class StateData (
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    @PrimaryKey val countryCode: String,
    @SerializedName("Deaths")
    val deaths: String,
    @SerializedName("Active")
    val active: Int,
    @SerializedName("Date")
    val date: Date
)
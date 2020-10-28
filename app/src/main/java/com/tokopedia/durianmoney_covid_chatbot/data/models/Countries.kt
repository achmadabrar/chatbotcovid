package com.tokopedia.durianmoney_covid_chatbot.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_code_table")
data class Countries (
    @SerializedName("Country")
    val country: String,
    @SerializedName("Slug")
    val slug: String,
    @SerializedName("ISO2")
    @PrimaryKey  val countryCode: String
)
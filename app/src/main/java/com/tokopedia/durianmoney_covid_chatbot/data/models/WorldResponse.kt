package com.tokopedia.durianmoney_covid_chatbot.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "world_total_table")
data class WorldResponse (
        @SerializedName("TotalConfirmed")
        val totalConfirmed: Long,
        @SerializedName("TotalDeaths")
        val totalDeaths: Long,
        @SerializedName("TotalRecovered")
        @PrimaryKey val totalRecovered: Long
)
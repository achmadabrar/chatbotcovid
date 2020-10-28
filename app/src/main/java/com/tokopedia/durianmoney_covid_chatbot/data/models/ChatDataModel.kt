package com.tokopedia.durianmoney_covid_chatbot.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.ListStateDataConverter
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.WorldResponseConverter

@Entity(tableName = "chat_table")
@TypeConverters(WorldResponseConverter::class, ListStateDataConverter::class)
data class ChatDataModel(
    @PrimaryKey val query: String,
    val isFromUser: Boolean,
    val botWorldResponse: WorldResponse?,
    val stateData: StateData?,
    val countryCode: String?,
    val date: String
)
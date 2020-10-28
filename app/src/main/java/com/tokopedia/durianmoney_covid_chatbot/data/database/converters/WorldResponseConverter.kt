package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.models.WorldResponse

class WorldResponseConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(worldResponse: WorldResponse): String {
            val type = object : TypeToken<WorldResponse>() {}.type
            return Gson().toJson(worldResponse, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): WorldResponse {
            val type = object : TypeToken<WorldResponse>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
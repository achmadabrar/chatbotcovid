package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData

class StateDataConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: StateData): String {
            val type = object : TypeToken<StateData>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): StateData {
            val type = object : TypeToken<StateData>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
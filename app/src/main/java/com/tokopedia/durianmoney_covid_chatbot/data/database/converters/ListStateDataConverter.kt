package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData

class ListStateDataConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<StateData>): String {
            val type = object : TypeToken<MutableList<StateData>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<StateData> {
            val type = object : TypeToken<MutableList<StateData>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
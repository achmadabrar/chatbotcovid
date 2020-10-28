package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.DbCovidbyState

class CovidDataByState {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<DbCovidbyState>): String {
            val type = object : TypeToken<MutableList<DbCovidbyState>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<DbCovidbyState> {
            val type = object : TypeToken<MutableList<DbCovidbyState>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.Countries

class ListCountryCodeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<Countries>): String {
            val type = object : TypeToken<MutableList<Countries>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<Countries> {
            val type = object : TypeToken<MutableList<Countries>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
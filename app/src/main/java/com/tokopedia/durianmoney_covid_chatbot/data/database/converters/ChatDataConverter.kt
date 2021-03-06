package com.tokopedia.durianmoney_covid_chatbot.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tokopedia.durianmoney_covid_chatbot.data.models.ChatDataModel

class ChatDataConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<ChatDataModel>): String {
            val type = object : TypeToken<MutableList<ChatDataModel>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<ChatDataModel> {
            val type = object : TypeToken<MutableList<ChatDataModel>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
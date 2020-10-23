package com.tokopedia.durianmoney_covid_chatbot.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class UserQueryDatabase : RoomDatabase() {

    abstract fun userQueryDao(): UserQueryDao

    companion object {
        private var instance: UserQueryDatabase? = null
        fun getInstance(context: Context): UserQueryDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                context.applicationContext,
                UserQueryDatabase::class.java,
                "user_query_database"
            ).fallbackToDestructiveMigration().build()
            return instance!!
        }
    }
}
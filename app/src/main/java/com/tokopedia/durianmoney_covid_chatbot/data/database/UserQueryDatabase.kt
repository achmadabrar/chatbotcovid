package com.tokopedia.durianmoney_covid_chatbot.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.DateTypeConverter
import com.tokopedia.durianmoney_covid_chatbot.data.models.Countries
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.models.WorldResponse

@Database(
    entities = [StateData::class, Countries::class, WorldResponse::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class UserQueryDatabase : RoomDatabase() {

    abstract fun chatDataDao(): ChatDataDao
    abstract fun countryCodeDao(): CountryCodeDao

    companion object {

        private var instance: UserQueryDatabase? = null
        private val LOCK = Any()
        const val DB_NAME = "query.db"

        @JvmStatic
        fun getInstance(context: Context): UserQueryDatabase {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            UserQueryDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
                return instance!!
            }
        }
    }
}
package com.tokopedia.durianmoney_covid_chatbot.core.di.module

import android.app.Application
import androidx.room.Room
import com.tokopedia.durianmoney_covid_chatbot.data.database.CountryCodeDao
import com.tokopedia.durianmoney_covid_chatbot.data.database.ChatDataDao
import com.tokopedia.durianmoney_covid_chatbot.data.database.UserQueryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): UserQueryDatabase {
        return Room
            .databaseBuilder(application, UserQueryDatabase::class.java, UserQueryDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: UserQueryDatabase): ChatDataDao {
        return appDataBase.chatDataDao()
    }

    @Provides
    fun provideCountryCodeDao(appDataBase: UserQueryDatabase): CountryCodeDao {
        return appDataBase.countryCodeDao()
    }
}
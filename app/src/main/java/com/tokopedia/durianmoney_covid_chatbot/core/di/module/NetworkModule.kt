package com.tokopedia.durianmoney_covid_chatbot.core.di.module

import com.google.gson.GsonBuilder
import com.tokopedia.durianmoney_covid_chatbot.BuildConfig
import com.tokopedia.durianmoney_covid_chatbot.data.networks.CovidApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providePostApi(retrofit: Retrofit): CovidApi {
        return retrofit.create(CovidApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}
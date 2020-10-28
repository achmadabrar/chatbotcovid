package com.tokopedia.durianmoney_covid_chatbot.data.networks

import com.tokopedia.durianmoney_covid_chatbot.data.models.Countries
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.models.WorldResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface CovidApi {

    companion object {
        const val LIVE = "live"
        const val COUNTRY = "country"
        const val QUERY = "slug"
        const val WORLD = "world"
        const val TOTAL = "total"
        const val COUNTRIES = "countries"
    }

    @GET("$LIVE/$COUNTRY/{$QUERY}")
    suspend fun getCovidDataByState(
        @Path("slug") slug: String?
    ): List<StateData>

    @GET("$WORLD/$TOTAL")
    suspend fun getAllCovidData(): WorldResponse

    @GET(COUNTRIES)
    suspend fun getAllCountries(): List<Countries>

}
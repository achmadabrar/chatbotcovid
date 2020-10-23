package com.tokopedia.durianmoney_covid_chatbot.data.networks

import com.tokopedia.durianmoney_covid_chatbot.data.models.CovidByStateResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    companion object {
        const val LIVE = "live"
        const val COUNTRY = "country/"
    }

    @GET("$LIVE/$COUNTRY")
    suspend fun getMerchantLocal(
        @Path("query") device: String = "indonesia"
    ): CovidByStateResponse

}
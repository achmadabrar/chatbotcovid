package com.tokopedia.durianmoney_covid_chatbot.data.datasources

import androidx.paging.PageKeyedDataSource
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseDataSource
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.networks.CovidApi
import javax.inject.Inject

class CovidByStateDataSource @Inject constructor(
    private val covidApi: CovidApi
) : PageKeyedDataSource<Int, StateData>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, StateData>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, StateData>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, StateData>) {
        TODO("Not yet implemented")
    }
}
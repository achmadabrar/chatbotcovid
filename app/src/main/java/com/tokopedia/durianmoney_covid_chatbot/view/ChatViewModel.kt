package com.tokopedia.durianmoney_covid_chatbot

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseViewModel
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.networks.NetworkState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : BaseViewModel<ChatViewModel>() {

    var covidByStateLiveData: LiveData<PagedList<StateData>> = MutableLiveData()
    var networkLiveData: LiveData<NetworkState> = MutableLiveData()
    var cacheByStateLiveData: LiveData<PagedList<StateData>> = MutableLiveData()

    companion object {
        const val CASESS_IN = "cases in"
        const val DEATHS = "deaths"
        const val CASES_TOTAL = "cases total"
        const val DEATHS_TOTAL = "deaths total"
    }

    fun getUserQuery(userQuery: String, context: Context) {
        if (userQuery.contains(CASESS_IN)) {
            getCountry(userQuery, CASESS_IN)
        } else if (userQuery.contains(DEATHS)) {
            getCountry(userQuery, DEATHS)
        } else if (userQuery.contains(CASES_TOTAL)) {
            getCountry(userQuery, CASES_TOTAL)
        } else if (userQuery.contains(DEATHS_TOTAL)){
            getCountry(userQuery, DEATHS_TOTAL)
        } else {
            Toast.makeText(context, "query not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun getCountry(query: String, delimiter: String) {
        val country  = query.substringAfter(delimiter)
        Log.d("country", "$country")
    }

    fun getCovidData(query: String) {
        ioScope.launch {
            repository.getCovidDataByState(query)
        }
        covidByStateLiveData = repository.covidByStateLiveData
        cacheByStateLiveData = repository.cacheLiveData
    }
}
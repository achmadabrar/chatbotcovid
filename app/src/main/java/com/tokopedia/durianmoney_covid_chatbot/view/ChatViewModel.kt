package com.tokopedia.durianmoney_covid_chatbot.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseViewModel
import com.tokopedia.durianmoney_covid_chatbot.data.database.ChatDataDao
import com.tokopedia.durianmoney_covid_chatbot.data.database.CountryCodeDao
import com.tokopedia.durianmoney_covid_chatbot.data.models.ChatDataModel
import com.tokopedia.durianmoney_covid_chatbot.data.models.Countries
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.models.WorldResponse
import com.tokopedia.durianmoney_covid_chatbot.data.networks.CovidApi
import com.tokopedia.durianmoney_covid_chatbot.data.networks.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val covidApi: CovidApi,
    private val countryCodeDao: CountryCodeDao,
    private val chatDataDao: ChatDataDao
) : BaseViewModel<ChatViewModel>() {

    var networkLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    val queryLiveData: MutableLiveData<String> = MutableLiveData()
    var responseWorldData: MutableLiveData<WorldResponse> = MutableLiveData()
    var responseStateData: MutableLiveData<List<StateData>>? = MutableLiveData()
    var responseCountries: MutableLiveData<List<Countries>> = MutableLiveData()

    var countryCodeString = ""

    var listUserQuery = mutableListOf<ChatDataModel?>()
    private var chatDataModel: ChatDataModel? = null

    private val supervisorJob = SupervisorJob()

    companion object {
        const val CASES = "cases "
        const val DEATHS = "deaths "
        const val TOTAL = "total"
        const val DEATHS_TOTAL = "deaths total"
        const val CASES_TOTAL = "cases total"
    }

    init {
        loadAllCountries()
    }

    fun getUserQuery(userQuery: String, context: Context) {
        if (userQuery.contains(CASES, ignoreCase = true)) {
            if (userQuery.equals(CASES_TOTAL, ignoreCase = true)) {
                getTotalData(userQuery, TOTAL)
            } else {
                getStateData(userQuery, CASES)
            }
        } else if (userQuery.contains(DEATHS, ignoreCase = true)) {
            if (userQuery.equals(DEATHS_TOTAL, ignoreCase = true)) {
                getTotalData(userQuery, TOTAL)
            } else {
                getStateData(userQuery, DEATHS)
            }
        } else {
            Toast.makeText(context, "query not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun getStateData(query: String, delimiter: String) {
        val countryCode = query.split(delimiter, ignoreCase = true)
        Log.d("countryCodeByTypingUser", countryCode.last())
        countryCodeString = countryCode.last()
        queryLiveData.postValue(query)
        loadBystateData(countryCode.last().toUpperCase())
    }

    fun getTotalData(query: String, delimiter: String) {
        val data = query.split(delimiter, ignoreCase = true)
        Log.d("country", data.last())
        queryLiveData.postValue(query)
        loadAllData()
    }

    fun loadBystateData(countryCode: String) {
        ioScope.launch(getJobErrorHandler() + supervisorJob) {
            val country = countryCodeDao.getCountry(countryCode)
            val stateDataCache = chatDataDao.getCovidData(countryCode)
            if (!stateDataCache.isNullOrEmpty()) {
                responseStateData?.postValue(stateDataCache)
            } else {
                if (!country.isNullOrEmpty()) {
                    val covidByState = covidApi.getCovidDataByState(country[0].slug)
                    Log.d("stateData", "$covidByState")
                    responseStateData?.postValue(covidByState)
                    chatDataDao.insertStateData(covidByState)
                    val data = chatDataDao.getAllCovidData()
                    Log.d("allStateData", "$data")
                } else {
                    val countries = countryCodeDao.getCountry(countryCode)
                    val allData = countryCodeDao.getAllCountryCodeData()
                    Log.d("error", "$allData")
                    Log.d("error_2", "$countries")
                }
            }
        }
    }

    fun loadAllData() {
        ioScope.launch {
            val stateDataCache = chatDataDao.getTotalData()
            if (stateDataCache != null) {
                responseWorldData.postValue(stateDataCache)
            } else {
                val covidByState = covidApi.getAllCovidData()
                Log.d("allData", "$covidByState")
                responseWorldData.postValue(covidByState)
                chatDataDao.insertTotalData(covidByState)
                val data = chatDataDao.getTotalData()
                Log.d("totalDataDao", "$data")
            }
        }
    }

    fun loadAllCountries() {
        ioScope.launch {
            val countries = covidApi.getAllCountries()
            Log.d("countries", "$countries")
            responseCountries.postValue(countries)
            countryCodeDao.insertCountryCode(countries)
            val localCountryCode = countryCodeDao.getAllCountryCodeData()
            Log.d("countriesDao", "$localCountryCode")
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(ChatViewModel::class.simpleName, "An error happened: $e")
        networkLiveData.postValue(NetworkState.fialed(e.localizedMessage))
        networkLiveData.postValue(NetworkState.FAILED)
    }

    fun invalidate() {
        supervisorJob.cancelChildren()
    }

    fun insertUserModel(
        query: String,
        isFromUser: Boolean,
        botWorldResponse: WorldResponse?,
        stateData: StateData?
    ) {
        chatDataModel = ChatDataModel(
            query = query,
            isFromUser = isFromUser,
            botWorldResponse = botWorldResponse,
            stateData = stateData,
            countryCode = countryCodeString.toUpperCase(Locale.ROOT),
            date = date()
        )
        insertUserQueryList(chatDataModel)
    }

    fun insertUserQueryList(chatDataModel: ChatDataModel?) {
        listUserQuery.add(chatDataModel)
    }

    fun date(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
        val dateformatted: String = dateFormat.format(date)
        return dateformatted
    }

}
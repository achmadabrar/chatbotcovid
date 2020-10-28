package com.tokopedia.durianmoney_covid_chatbot.external

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import com.tokopedia.durianmoney_covid_chatbot.data.networks.Results
import com.tokopedia.durianmoney_covid_chatbot.data.networks.Results.Companion.loading


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

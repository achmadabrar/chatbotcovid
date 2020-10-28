package com.tokopedia.durianmoney_covid_chatbot.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.durianmoney_covid_chatbot.R
import com.tokopedia.durianmoney_covid_chatbot.data.models.ChatDataModel
import kotlinx.android.synthetic.main.item_chat_user.view.*
import java.lang.String

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val CASES_TOTAL = "cases total"
        private const val DEATHS_TOTAL = "deaths total"
    }

    fun bindItem(item: ChatDataModel) {
        with(itemView) {
            if (item.isFromUser) {
                card_user.visibility = View.VISIBLE
                card_bot.visibility = View.GONE
                user_text.text = item.query
                date_user.text = item.date
            } else {
                card_user.visibility = View.GONE
                card_bot.visibility = View.VISIBLE
                if (item.query.contains("cases", ignoreCase = true) && !item.query.equals(CASES_TOTAL, ignoreCase = true)) {
                    date_bot.text = item.date
                    bot_text.setText(String.format(resources.getString(R.string.cases_country_code), item.countryCode, item.stateData?.active.toString()))
                } else if (item.query.equals(CASES_TOTAL, ignoreCase = true)) {
                    date_bot.text = item.date
                    bot_text.setText(String.format(resources.getString(R.string.total_active_cases), item.botWorldResponse?.totalConfirmed.toString()))
                } else if (item.query.equals(DEATHS_TOTAL, ignoreCase = true)){
                    date_bot.text = item.date
                    bot_text.setText(String.format(resources.getString(R.string.total_deaths), item.botWorldResponse?.totalDeaths.toString()))
                } else {
                    date_bot.text = item.date
                    bot_text.setText(String.format(resources.getString(R.string.deaths_country_code), item.countryCode, item.stateData?.deaths.toString()))
                }
            }

        }
    }
}
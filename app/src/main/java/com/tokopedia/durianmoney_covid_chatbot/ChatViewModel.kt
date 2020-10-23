package com.tokopedia.durianmoney_covid_chatbot

import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseViewModel
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
): BaseViewModel<ChatViewModel>() {
}
package com.tokopedia.durianmoney_covid_chatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ChatActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun handleQuery() {
        etChat.text.toString()
    }

    private fun onClickButton() {
        btnChat.setOnClickListener {

        }
    }

    companion object {


    }
}
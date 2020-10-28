package com.tokopedia.durianmoney_covid_chatbot

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ChatActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: ChatViewModel

    var adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)
        onClickButton()
        viewModel.covidByStateLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                //adapter.submitList(it)
            }
        })

        viewModel.cacheByStateLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                //adapter.submitList(it)
            }
        })

        viewModel.networkLiveData.observe(this, Observer {
            adapter.networkState = it
        })
        loadRecyclerView()

    }

    private fun loadRecyclerView() {
        recyclerChat.adapter = adapter
        recyclerChat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    fun onClickButton() {
        btnChat.setOnClickListener {
            viewModel.getUserQuery(etChat.text.toString(), this)
        }
    }

    companion object {


    }
}
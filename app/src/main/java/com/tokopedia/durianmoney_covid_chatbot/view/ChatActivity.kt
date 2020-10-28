package com.tokopedia.durianmoney_covid_chatbot.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokopedia.durianmoney_covid_chatbot.R
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ChatActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: ChatViewModel

    var adapter: ChatAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)
        onClickButton()

        viewModel.responseWorldData.observe(this, Observer {
         if (it != null) {
             viewModel.listUserQuery.last()?.query?.let { query ->
                 viewModel.insertUserModel(
                     query,
                     false,
                     it,
                     null
                 )
             }
             adapter = ChatAdapter(viewModel.listUserQuery)
             loadRecyclerView()
            }
        })

        viewModel.responseStateData?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                viewModel.listUserQuery.last()?.query?.let { query ->
                    viewModel.insertUserModel(
                        query,
                        false,
                        null,
                        it.last()
                    )
                }
                adapter = ChatAdapter(viewModel.listUserQuery)
                loadRecyclerView()
            } else {
                viewModel.listUserQuery.last()?.query?.let { query ->
                    viewModel.insertUserModel(
                        query,
                        false,
                        null,
                        null
                    )
                }
                adapter = ChatAdapter(viewModel.listUserQuery)
                loadRecyclerView()
            }
        })

        viewModel.queryLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                observeData(it)
            }
        })
    }

    private fun observeData(query: String) {
        viewModel.insertUserModel(query, true, null, null)
        adapter = ChatAdapter(viewModel.listUserQuery)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        recyclerChat.adapter = adapter
        recyclerChat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter?.itemCount?.let { recyclerChat.smoothScrollToPosition(it - 1) }

    }

    fun onClickButton() {
        btnChat.setOnClickListener {
            viewModel.getUserQuery(etChat.text.toString(), this)

        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.invalidate()
    }
}
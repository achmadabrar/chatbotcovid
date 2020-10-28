package com.tokopedia.durianmoney_covid_chatbot.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.durianmoney_covid_chatbot.R
import com.tokopedia.durianmoney_covid_chatbot.data.models.UserQueryModel

class NewChatAdapter(
    private val query: MutableList<UserQueryModel?>
): RecyclerView.Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_user,parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        query[position]?.let { holder.bindItem(it) }
    }

    override fun getItemCount(): Int {
        return query.size
    }
}
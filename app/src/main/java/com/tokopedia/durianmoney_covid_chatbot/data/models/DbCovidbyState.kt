package com.tokopedia.durianmoney_covid_chatbot.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_covid_by_state")
data class DbCovidbyState (
    val query: String?,
    @PrimaryKey val stateData: MutableList<StateData>
)
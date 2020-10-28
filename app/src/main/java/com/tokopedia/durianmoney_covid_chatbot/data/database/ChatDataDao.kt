package com.tokopedia.durianmoney_covid_chatbot.data.database

import androidx.room.*
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.ChatDataConverter
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.WorldResponseConverter
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import com.tokopedia.durianmoney_covid_chatbot.data.models.WorldResponse

@Dao
@TypeConverters(ChatDataConverter::class, WorldResponseConverter::class)
interface ChatDataDao {

    @Query("SELECT * FROM state_data_table")
    suspend fun getAllCovidData(): List<StateData>?

    @Query("SELECT * FROM state_data_table WHERE `countryCode` == :countryCode")
    suspend fun getCovidData(countryCode: String?): List<StateData>?
/*
    @Query("DELETE FROM covid_by_state_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListProduct(expiredDate: Date?)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStateData(listStateData: List<StateData>)

    @Query("SELECT * FROM world_total_table")
    suspend fun getTotalData(): WorldResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTotalData(listTotalData: WorldResponse)
}
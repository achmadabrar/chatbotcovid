package com.tokopedia.durianmoney_covid_chatbot.data.database

import androidx.room.*
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.CovidDataByState
import com.tokopedia.durianmoney_covid_chatbot.data.models.DbCovidbyState
import java.util.*

@Dao
@TypeConverters(CovidDataByState::class)
interface UserQueryDao {

    @Query("SELECT * FROM covid_by_state_table")
    suspend fun getAllCovidData(): List<DbCovidbyState>

    @Query("SELECT * FROM covid_by_state_table WHERE `country` == :country ")
    suspend fun getCovidData(country: String?): List<DbCovidbyState>

    @Query("DELETE FROM covid_by_state_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListProduct(expiredDate: Date?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListProduct(listProduct: List<DbCovidbyState>)
}
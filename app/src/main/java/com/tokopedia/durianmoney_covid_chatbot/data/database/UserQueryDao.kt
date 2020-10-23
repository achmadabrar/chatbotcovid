package com.tokopedia.durianmoney_covid_chatbot.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tokopedia.durianmoney_covid_chatbot.data.models.DbCovidbyState
import java.util.*

interface UserQueryDao {
    @Query("SELECT * FROM db_covid_by_state WHERE `query` == :queryData ")
    suspend fun getProduct(queryData: String?): List<DbCovidbyState>

    @Query("SELECT * FROM db_covid_by_state WHERE `query` == :queryData ")
    fun getLiveProduct(queryData: String?): LiveData<List<DbCovidbyState>>

    /*@Query("DELETE FROM search_product_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListProduct(expiredDate: Date?)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListProduct(listProduct: List<DbCovidbyState>)
}
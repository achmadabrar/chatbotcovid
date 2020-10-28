package com.tokopedia.durianmoney_covid_chatbot.data.database

import androidx.room.*
import com.tokopedia.durianmoney_covid_chatbot.data.database.converters.ListCountryCodeConverter
import com.tokopedia.durianmoney_covid_chatbot.data.models.Countries

@Dao
@TypeConverters(ListCountryCodeConverter::class)
interface CountryCodeDao {
    @Query("SELECT * FROM country_code_table")
    suspend fun getAllCountryCodeData(): List<Countries>

    @Query("SELECT * FROM country_code_table WHERE `countryCode` == :countryCode ")
    suspend fun getCountry(countryCode: String?): List<Countries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryCode(listProduct: List<Countries>)
}
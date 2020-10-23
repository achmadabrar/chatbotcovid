package com.tokopedia.durianmoney_covid_chatbot.core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository<DataStore> {
    protected var localDataStore: DataStore? = null
    protected var remoteDataStore: DataStore? = null

    protected val ioScope = CoroutineScope(Dispatchers.Default)

    fun init(localDataStore: DataStore, remoteDataStore: DataStore) {
        this.localDataStore = localDataStore
        this.remoteDataStore = remoteDataStore
    }
}
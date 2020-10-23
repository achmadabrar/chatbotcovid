package com.tokopedia.durianmoney_covid_chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseRepository
import com.tokopedia.durianmoney_covid_chatbot.data.database.ChatDataStore
import com.tokopedia.durianmoney_covid_chatbot.data.database.UserQueryDao
import com.tokopedia.durianmoney_covid_chatbot.data.models.StateData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val userQueryDao: UserQueryDao,
) : BaseRepository<ChatDataStore>() {

    fun observeCovidDataByState(query: String?) = reloadRemoteChatByState(query)

    fun observeCovidDataWorld(query: String?) = reloadCovidDataWorld(query)

    private fun reloadRemoteChatByState(query: String?): LiveData<PagedList<StateData>> {
        val chatByStateFactory =
            ChatByStateFactory(
                ioScope,
                query,
                searchApi,
                searchProductDao,
                productDataSource
            )

        val configuration = PagedList.Config.Builder()
            .setPageSize(NewProductDataSource.PAGE_SIZE)
            .setInitialLoadSizeHint(NewProductDataSource.INITIAL_PAGE)
            .setPrefetchDistance(NewProductDataSource.PREFECTH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        Transformations.switchMap(chatByStateFactory.dataSourceLiveData) {
            it.networkStatusLiveData
        }

        return LivePagedListBuilder<Int, ProductGraphQl>(chatByStateFactory, configuration).build()
    }

    private fun reloadCovidDataWorld(query: String?): LiveData<PagedList<Shop>> {
        val merchantFactory =
            LocalMerhantFactory(
                ioScope,
                query,
                searchApi,
                searchShopDao,
                merchantDataSource
            )
        val config = PagedList.Config.Builder()
            .setPageSize(LocalMerchantDataSource.PAGE_SIZE)
            .setPrefetchDistance(LocalMerchantDataSource.PREFECTH_DISTANCE)
            .setInitialLoadSizeHint(LocalMerchantDataSource.INITIAL_PAGE)
            .setEnablePlaceholders(false)
            .build()

        Transformations.switchMap(merchantFactory.dataSourceLiveData) {
            it.networkStatusLiveData
        }

        return LivePagedListBuilder<Int, Shop>(merchantFactory, config).build()
    }

    fun getQueryProduct(query: String?): HashMap<String, String> {
        val data = HashMap<String, String>()
        data.put(
            "query", QueryData()
                .queryProduct(query)
        )
        return data
    }

    fun getQueryMerchant(query: String?): HashMap<String, String> {
        val data = HashMap<String, String>()
        data.put(
            "query", QueryData()
                .queryShop(query)
        )
        return data
    }


    fun observeLiveProduct(query: String?) = resultLiveData(
        databaseQuery = {searchProductDao.getLiveProduct(query)},
        networkCall = {productDataSource.getProductGql(getQueryProduct(query))},
        saveCallResult = {
            val listProducts = mutableListOf<SearchProduct>()
            listProducts.add(it.data.searchProduct)
            searchProductDao.insertListProduct(listProducts)
        }
    )

    fun observeLiveMerchant(query: String?) = resultLiveData(
        databaseQuery = {searchShopDao.getShopLiveData(query)},
        networkCall = {merchantDataSource.getShopGql(getQueryMerchant(query))},
        saveCallResult = {
            val listShop = mutableListOf<AceSearchShop>()
            listShop.add(it.data.aceSearchShop)
            searchShopDao.insertListShop(listShop)
        }
    )


}
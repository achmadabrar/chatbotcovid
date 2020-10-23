package com.tokopedia.durianmoney_covid_chatbot.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokopedia.durianmoney_covid_chatbot.ChatViewModel
import com.tokopedia.durianmoney_covid_chatbot.core.di.ViewModelFactory
import com.tokopedia.durianmoney_covid_chatbot.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    internal abstract fun chatViewModel(viewModel: ChatViewModel): ViewModel
}
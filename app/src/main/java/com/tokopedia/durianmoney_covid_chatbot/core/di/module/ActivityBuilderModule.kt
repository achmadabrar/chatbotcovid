package com.tokopedia.durianmoney_covid_chatbot.core.di.module

import com.tokopedia.durianmoney_covid_chatbot.view.ChatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesChatActivity(): ChatActivity

}
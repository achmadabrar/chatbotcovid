package com.tokopedia.durianmoney_covid_chatbot.core.di.component

import android.app.Application
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseApplication
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.ActivityBuilderModule
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.NetworksModule
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class,
        NetworksModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        abstract fun networkModule(networkModule: NetworksModule): Builder
    }
}
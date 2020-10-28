package com.tokopedia.durianmoney_covid_chatbot.core.di.component

import android.app.Application
import com.tokopedia.durianmoney_covid_chatbot.core.base.BaseApplication
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.ActivityBuilderModule
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.DatabaseModule
import com.tokopedia.durianmoney_covid_chatbot.core.di.module.NetworkModule
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
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        abstract fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
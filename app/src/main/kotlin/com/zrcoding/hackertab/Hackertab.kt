package com.zrcoding.hackertab

import android.app.Application
import android.content.Context
import com.zrcoding.hackertab.home.homeModule
import com.zrcoding.hackertab.network.networkModule
import com.zrcoding.hackertab.settings.settingsModule
import org.koin.core.context.startKoin
import org.koin.dsl.module


class Hackertab : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

            modules(
                module { single<Context> { this@Hackertab } },
                homeModule,
                settingsModule,
                networkModule
            )
        }
    }
}
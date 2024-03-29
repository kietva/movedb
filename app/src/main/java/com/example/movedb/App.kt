package com.example.movedb

import android.app.Application
import com.example.movedb.di.apiModule
import com.example.movedb.di.dataModule
import com.example.movedb.di.networkModule
import com.example.movedb.di.useCaseModule
import com.example.movedb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                apiModule,
                dataModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}
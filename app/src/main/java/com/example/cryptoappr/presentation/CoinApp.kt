package com.example.cryptoappr.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoappr.data.workManager.CoinWorkerFactory
import com.example.cryptoappr.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    @Inject
    lateinit var workerFactory: CoinWorkerFactory
    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(
            workerFactory
        ).build()
}
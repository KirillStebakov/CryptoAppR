package com.example.cryptoappr.di

import android.app.Application
import com.example.cryptoappr.data.database.AppDatabase
import com.example.cryptoappr.data.database.CoinInfoDao
import com.example.cryptoappr.data.network.ApiFactory
import com.example.cryptoappr.data.network.ApiService
import com.example.cryptoappr.data.repository.CoinRepositoryImpl
import com.example.cryptoappr.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
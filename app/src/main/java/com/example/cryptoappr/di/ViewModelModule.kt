package com.example.cryptoappr.di

import androidx.lifecycle.ViewModel
import com.example.cryptoappr.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindViewModel(impl: CoinViewModel): ViewModel
}
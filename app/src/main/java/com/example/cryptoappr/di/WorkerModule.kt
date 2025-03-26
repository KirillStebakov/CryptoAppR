package com.example.cryptoappr.di

import com.example.cryptoappr.data.workManager.ChildWorkerFactory
import com.example.cryptoappr.data.workManager.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindWorker(impl: RefreshDataWorker.Factory): ChildWorkerFactory
}
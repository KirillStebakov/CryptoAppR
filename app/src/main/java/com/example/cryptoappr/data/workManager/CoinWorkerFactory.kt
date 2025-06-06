package com.example.cryptoappr.data.workManager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class CoinWorkerFactory @Inject constructor(
    private val workerProvider: @JvmSuppressWildcards Map<
            Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshDataWorker::class.qualifiedName -> {
                val factory = workerProvider[RefreshDataWorker::class.java]?.get()
                factory?.create(appContext, workerParameters)
            }
            else -> null
        }
    }
}
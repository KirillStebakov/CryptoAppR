package com.example.cryptoappr.data.workManager

import android.content.Context
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(
        context: Context,
        workerParameters: WorkerParameters
    ): RefreshDataWorker
}
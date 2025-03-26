package com.example.cryptoappr.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import com.example.cryptoappr.data.database.CoinInfoDao
import com.example.cryptoappr.data.workManager.RefreshDataWorker
import com.example.cryptoappr.domain.CoinInfo
import com.example.cryptoappr.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper:CoinMapper
) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList()
            .map { list -> list.map { mapper.mapDbModelToEntity(it) } }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
            .map { mapper.mapDbModelToEntity(it) }
    }

    override fun loadData() {
        val workManager = androidx.work.WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.workRequest()
        )
    }

}
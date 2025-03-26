package com.example.cryptoappr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoappr.domain.CoinInfo
import com.example.cryptoappr.domain.GetCoinInfoListUseCase
import com.example.cryptoappr.domain.GetCoinInfoUseCase
import com.example.cryptoappr.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase:GetCoinInfoUseCase,
    private val getCoinInfoListUseCase:GetCoinInfoListUseCase,
    private val loadDataUseCase:LoadDataUseCase,
) : ViewModel() {

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fSym)
    }

    val coinInfoList = getCoinInfoListUseCase()

}
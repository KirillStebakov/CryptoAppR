package com.example.cryptoappr.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNamesListDto (
    @SerializedName("Data")
    val names: List<CoinNameContainerDto>? = null
)

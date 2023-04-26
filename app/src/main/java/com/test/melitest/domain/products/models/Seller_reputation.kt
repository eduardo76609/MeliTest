package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Seller_reputation (

    @SerializedName("level_id") val level_id : String,
    @SerializedName("power_seller_status") val power_seller_status : String,
    @SerializedName("transactions") val transactions : Transactions,
    @SerializedName("metrics") val metrics : Metrics
)
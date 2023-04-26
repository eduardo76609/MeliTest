package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Metrics (

    @SerializedName("sales") val sales : Sales,
    @SerializedName("claims") val claims : Claims,
    @SerializedName("delayed_handling_time") val delayed_handling_time : Delayed_handling_time,
    @SerializedName("cancellations") val cancellations : Cancellations
)
package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Transactions (

    @SerializedName("canceled") val canceled : Int,
    @SerializedName("completed") val completed : Int,
    @SerializedName("period") val period : String,
    @SerializedName("ratings") val ratings : Ratings,
    @SerializedName("total") val total : Int
)
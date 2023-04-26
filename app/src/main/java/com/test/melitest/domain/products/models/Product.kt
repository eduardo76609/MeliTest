package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("condition") val condition : String,
    @SerializedName("thumbnail_id") val thumbnail_id : String,
    @SerializedName("price") val price : Int,
)
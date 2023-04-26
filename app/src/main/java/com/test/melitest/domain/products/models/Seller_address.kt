package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Seller_address (

    @SerializedName("comment") val comment : String,
    @SerializedName("address_line") val address_line : String,
    @SerializedName("zip_code") val zip_code : String,
    @SerializedName("id") val id : String,
    @SerializedName("latitude") val latitude : String,
    @SerializedName("longitude") val longitude : String,
    @SerializedName("country") val country : Country,
    @SerializedName("state") val state : State,
    @SerializedName("city") val city : City
)
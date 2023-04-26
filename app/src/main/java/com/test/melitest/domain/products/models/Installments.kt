package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Installments (

	@SerializedName("quantity") val quantity : Int,
	@SerializedName("amount") val amount : Double,
	@SerializedName("rate") val rate : Int,
	@SerializedName("currency_id") val currency_id : String
)
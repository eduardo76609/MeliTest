package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Claims (

	@SerializedName("period") val period : String,
	@SerializedName("rate") val rate : Int,
	@SerializedName("value") val value : Int,
	@SerializedName("excluded") val excluded : Excluded
)
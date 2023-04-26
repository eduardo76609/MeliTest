package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Excluded (

	@SerializedName("real_value") val real_value : Int,
	@SerializedName("real_rate") val real_rate : Int
)
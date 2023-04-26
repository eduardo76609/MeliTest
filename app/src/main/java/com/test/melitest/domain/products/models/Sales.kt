package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Sales (

	@SerializedName("period") val period : String,
	@SerializedName("completed") val completed : Int
)
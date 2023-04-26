package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Paging (

	@SerializedName("total") val total : Int,
	@SerializedName("primary_results") val primary_results : Int,
	@SerializedName("offset") val offset : Int,
	@SerializedName("limit") val limit : Int
)
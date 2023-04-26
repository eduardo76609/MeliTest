package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Filters (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("type") val type : String,
	@SerializedName("values") val values : List<Values>
)
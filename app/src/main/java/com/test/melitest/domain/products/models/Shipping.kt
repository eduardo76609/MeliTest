package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Shipping (

	@SerializedName("store_pick_up") val store_pick_up : Boolean,
	@SerializedName("free_shipping") val free_shipping : Boolean,
	@SerializedName("logistic_type") val logistic_type : String,
	@SerializedName("mode") val mode : String,
	@SerializedName("tags") val tags : List<String>,
	@SerializedName("promise") val promise : String
)
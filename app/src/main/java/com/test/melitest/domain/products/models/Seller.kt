package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Seller (

	@SerializedName("id") val id : Int,
	@SerializedName("nickname") val nickname : String,
	@SerializedName("car_dealer") val car_dealer : Boolean,
	@SerializedName("real_estate_agency") val real_estate_agency : Boolean,
	@SerializedName("registration_date") val registration_date : String,
	@SerializedName("tags") val tags : List<String>,
	@SerializedName("car_dealer_logo") val car_dealer_logo : String,
	@SerializedName("permalink") val permalink : String,
	@SerializedName("seller_reputation") val seller_reputation : Seller_reputation
)
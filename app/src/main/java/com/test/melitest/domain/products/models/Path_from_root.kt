package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Path_from_root (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String
)
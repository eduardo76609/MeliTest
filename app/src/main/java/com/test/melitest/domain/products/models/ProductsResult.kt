package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class ProductsResult (
	@SerializedName("results") val results : List<Product>
)
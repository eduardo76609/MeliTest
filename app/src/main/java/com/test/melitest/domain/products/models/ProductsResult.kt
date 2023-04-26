package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class ProductsResult (
	//@SerializedName("site_id") val site_id : String,
	//@SerializedName("country_default_time_zone") val country_default_time_zone : String,
	//@SerializedName("query") val query : String,
	//@SerializedName("paging") val paging : Paging,
	@SerializedName("results") val results : List<Product>,
	//@SerializedName("sort") val sort : Sort,
	//@SerializedName("available_sorts") val available_sorts : List<Available_sorts>,
	//@SerializedName("filters") val filters : List<Filters>,
	//@SerializedName("available_filters") val available_filters : List<Available_filters>
)
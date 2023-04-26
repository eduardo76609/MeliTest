package com.test.melitest.data.products.remote

import com.test.melitest.domain.products.models.ProductsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("search")
    suspend fun searchProducts (
        @Query("q") value: String
    ): Response<ProductsResult>

}
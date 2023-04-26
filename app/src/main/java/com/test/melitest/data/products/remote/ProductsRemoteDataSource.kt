package com.test.melitest.data.products.remote

import com.test.melitest.core.network.ManageRequest
import com.test.melitest.core.network.RequestResult
import com.test.melitest.domain.products.models.ProductsResult
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(private val productsApi: ProductsApi) :
    ManageRequest() {

    suspend fun searchProducts(value: String): RequestResult<ProductsResult> {
        return safeApiCall { productsApi.searchProducts(value) }
    }

}
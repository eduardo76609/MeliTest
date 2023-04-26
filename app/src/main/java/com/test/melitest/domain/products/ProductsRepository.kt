package com.test.melitest.domain.products

import com.test.melitest.core.network.RequestResult
import com.test.melitest.domain.products.models.ProductsResult

interface ProductsRepository {
    suspend fun searchProducts(value: String): RequestResult<ProductsResult>
}
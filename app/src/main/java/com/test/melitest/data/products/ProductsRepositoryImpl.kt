package com.test.melitest.data.products

import com.test.melitest.core.network.RequestResult
import com.test.melitest.data.products.remote.ProductsRemoteDataSource
import com.test.melitest.domain.products.ProductsRepository
import com.test.melitest.domain.products.models.ProductsResult
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsRemoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {

    override suspend fun searchProducts(value: String): RequestResult<ProductsResult> {
        return productsRemoteDataSource.searchProducts(value)
    }

}
package com.test.melitest.domain.products.usecases

import com.test.melitest.core.network.RequestResult
import com.test.melitest.domain.products.ProductsRepository
import com.test.melitest.domain.products.models.ProductsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository) {

    suspend fun execute(value: String): RequestResult<ProductsResult> {
        return withContext(Dispatchers.IO){
            productsRepository.searchProducts(value)
        }
    }

}
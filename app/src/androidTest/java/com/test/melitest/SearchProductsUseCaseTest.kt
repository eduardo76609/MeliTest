package com.test.melitest

import com.test.melitest.domain.products.ProductsRepository
import com.test.melitest.domain.products.usecases.SearchProductsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchProductsUseCaseTest {

    private val productsRepository = mockk<ProductsRepository>(relaxed = true)
    private val searchProductsUseCase = SearchProductsUseCase(productsRepository)

    @Test
    fun exampleTest () {

        runBlocking {
            searchProductsUseCase.execute("moto")
        }

        coVerify(exactly = 1) {
            productsRepository.searchProducts("moto")
        }
    }

}
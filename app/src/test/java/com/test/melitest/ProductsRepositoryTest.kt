package com.test.melitest

import com.test.melitest.core.network.RequestResult
import com.test.melitest.data.products.ProductsRepositoryImpl
import com.test.melitest.data.products.remote.ProductsRemoteDataSource
import com.test.melitest.domain.products.models.Product
import com.test.melitest.domain.products.models.ProductsResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ProductRepositoryTest {

    @Test
    fun givenARemoteDataSourceWithParameterWhenRepositoryIsExecutedThenShouldCallRemoteDataSourceMethodAndReturnSuccess() {
        //Arrange
        val productRemoteDataSource = mockk<ProductsRemoteDataSource>()
        val product = Product(
            id = "ABCD4",
            title = "Motorola G8 plus",
            condition = "New",
            thumbnail_id = "www.image.com",
            price = 1200
        )
        val productsResult = ProductsResult(results = listOf(product))
        val mockResult: RequestResult<ProductsResult> = RequestResult.Success(productsResult)
        val repository = ProductsRepositoryImpl(productRemoteDataSource)

        coEvery {
            productRemoteDataSource.searchProducts("motorola")
        } answers {
            mockResult
        }

        //Act
        val response: RequestResult<ProductsResult> = runBlocking {
            repository.searchProducts("motorola")
        }

        //Assert
        coVerify(exactly = 1) {
            productRemoteDataSource.searchProducts("motorola")
        }

        Assert.assertTrue(response is RequestResult.Success)
    }
}
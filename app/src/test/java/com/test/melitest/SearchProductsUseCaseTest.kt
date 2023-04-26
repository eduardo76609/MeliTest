package com.test.melitest

import com.test.melitest.core.network.ErrorResponse
import com.test.melitest.core.network.RequestResult
import com.test.melitest.domain.products.ProductsRepository
import com.test.melitest.domain.products.models.Product
import com.test.melitest.domain.products.models.ProductsResult
import com.test.melitest.domain.products.usecases.SearchProductsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SearchProductsUseCaseTest {

    @Test
    fun givenAqueryParameterWhenUseCaseIsExecuteThenShouldCallRepositoryOneTime() {
        //Arrange
        val repository = mockk<ProductsRepository>()
        val result: RequestResult<ProductsResult> = RequestResult.Success(ProductsResult(results = emptyList()))
        val searchProductsUseCase = SearchProductsUseCase(repository)
        coEvery {
            repository.searchProducts("moto")
        } answers  {
            result
        }

        //Act
        runBlocking {
            searchProductsUseCase.execute("moto")
        }

        //Assert
        coVerify(exactly = 1) {
            repository.searchProducts("moto")
        }
    }

    @Test
    fun givenAemptyQueryParameterWhenUseCaseIsExecuteThenShouldReturnEmptyList() {
        //Arrange
        val repository = mockk<ProductsRepository>()
        val result: RequestResult<ProductsResult> = RequestResult.Success(ProductsResult(results = emptyList()))
        val searchProductsUseCase = SearchProductsUseCase(repository)

        coEvery {
            repository.searchProducts("")
        } answers  {
            result
        }

        //Act
        val response = runBlocking {
            searchProductsUseCase.execute("")
        }

        //Assert
        Assert.assertEquals(result,response)
    }

    @Test
    fun givenAqueryParameterWhenUseCaseIsExecuteThenShouldReturnFilledList() {
        //Arrange
        val repository = mockk<ProductsRepository>()
        val searchProductsUseCase = SearchProductsUseCase(repository)

        val product0 = Product(id = "1A", title = "celular", condition = "Nuevo", thumbnail_id = "ABC1", price = 1000)
        val product1 = Product(id = "2A", title = "Tablet", condition = "Nuevo", thumbnail_id = "ABC2", price = 1500)
        val mockResult: RequestResult<ProductsResult> = RequestResult.Success(ProductsResult(results = listOf(product0, product1)))
        val expectedResult = listOf(product0, product1)

        coEvery {
            repository.searchProducts("product")
        } answers  {
            mockResult
        }

        //Act
        val response = runBlocking {
            searchProductsUseCase.execute("product")
        }

        //Assert
        Assert.assertTrue(response is RequestResult.Success)
        Assert.assertEquals(expectedResult,(response as RequestResult.Success).data?.results)
    }

    @Test
    fun givenAqueryParameterWhenUseCaseIsExecuteThenShouldReturnFailure() {
        //Arrange
        val repository = mockk<ProductsRepository>()
        val searchProductsUseCase = SearchProductsUseCase(repository)
        val errorResponse = ErrorResponse("Error message","error", 400)
        val mockErrorResult: RequestResult<ProductsResult> = RequestResult.Failure(errorResponse)

        coEvery {
            repository.searchProducts("mazda")
        } answers  {
            mockErrorResult
        }

        //Act
        val response = runBlocking {
            searchProductsUseCase.execute("mazda")
        }

        //Assert
        Assert.assertTrue(response is RequestResult.Failure)
    }
}
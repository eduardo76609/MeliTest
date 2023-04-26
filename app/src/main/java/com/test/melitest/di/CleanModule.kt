package com.test.melitest.di

import com.test.melitest.data.products.ProductsRepositoryImpl
import com.test.melitest.data.products.remote.ProductsRemoteDataSource
import com.test.melitest.domain.products.ProductsRepository
import com.test.melitest.domain.products.usecases.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityRetainedComponent::class, SingletonComponent::class)
object CleanModule {

    @Provides
    fun bindProductsRepository(
        productsRemoteDataSource: ProductsRemoteDataSource,
    ): ProductsRepository = ProductsRepositoryImpl(productsRemoteDataSource)

    @Provides
    fun provideSearchProductsUseCase(productsRepository: ProductsRepository) =
        SearchProductsUseCase(productsRepository)

}

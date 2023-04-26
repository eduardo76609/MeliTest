package com.test.melitest.presentation.navigation

sealed class AppScreens(val route: String){
    object ProductsScreen: AppScreens("products_screen")
    object SearchProductsScreen: AppScreens("search_products_screen")
    object ProductDetailsScreen: AppScreens("product_details_screen")
}

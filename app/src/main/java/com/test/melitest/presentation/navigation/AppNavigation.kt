package com.test.melitest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.melitest.presentation.product_details.ProductDetailsScreen
import com.test.melitest.presentation.products.ProductsScreen
import com.test.melitest.presentation.products.ProductsViewModel

@Composable
fun AppNavigation() {

    val productsViewModel: ProductsViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.ProductsScreen.route
    ) {

        composable(route = AppScreens.ProductsScreen.route) {
            ProductsScreen(productsViewModel, navController)
        }

        composable(
            route = AppScreens.ProductDetailsScreen.route + "/{position}",
            arguments = listOf(navArgument(name = "position") {
                type = NavType.IntType
            })
        ) {
            ProductDetailsScreen(
                productsViewModel = productsViewModel,
                position = it.arguments?.getInt("position")
            )
        }

    }

}
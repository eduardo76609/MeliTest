package com.test.melitest.presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.test.melitest.R
import com.test.melitest.domain.products.models.Product
import com.test.melitest.presentation.products.compose.ProductCard
import com.test.melitest.presentation.search_products.SearchProductsScreen
import com.test.melitest.presentation.theme.DodgerBlue
import com.test.melitest.presentation.theme.Turbo

@Composable
fun ProductsScreen(productsViewModel: ProductsViewModel, navController: NavController) {

    val mutableProductsStates = productsViewModel.mutableProductsStates.value

    Scaffold(
        topBar = { TopBar(productViewModel = productsViewModel) }
    ) { padding ->
        padding
        BodyContent(
            productsStates = mutableProductsStates,
            productsViewModel = productsViewModel,
            navController = navController
        )
    }
}

@Composable
fun TopBar(productViewModel: ProductsViewModel) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Turbo,
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-16).dp),
            ) {
                SearchButton(productViewModel = productViewModel)
            }
        },
        actions = {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "ShoppingCart"
            )
        }
    )
}

@Composable
private fun SearchButton(productViewModel: ProductsViewModel) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { productViewModel.setSearchingState() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Search",
                tint = Color.LightGray
            )
            Text(

                text = productViewModel.searchTextState.value.ifEmpty { stringResource(id = R.string.product_screen_search_on_meli) },
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun BodyContent(
    productsStates: ProductsStates,
    productsViewModel: ProductsViewModel,
    navController: NavController
) {

    when (productsStates) {
        is ProductsStates.Initial -> ShowStartMessage()
        is ProductsStates.Searching -> SearchFullScreenDialog(productViewModel = productsViewModel)
        is ProductsStates.Loading -> LoadingIndicator()
        is ProductsStates.ProductsData -> ShowProducts(
            products = productsStates.products,
            navController = navController
        )

        is ProductsStates.Error -> ShowError(message = productsStates.error)
    }
}

@Composable
fun ShowStartMessage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .size(100.dp),
            imageVector = Icons.Outlined.Home,
            contentDescription = "Notifications",
            tint = Color.LightGray
        )
        Text(stringResource(id = R.string.product_screen_click_to_start))
    }
}

@Composable
fun SearchFullScreenDialog(productViewModel: ProductsViewModel) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            contentColor = Color.Red,
            topBar = {
                SearchProductsScreen(
                    text = productViewModel.searchTextState.value,
                    onTextChange = { productViewModel.updateSearchTextState(newValue = it) },
                    onSearchClicked = { productViewModel.searchProducts(it) },
                    goBack = {
                        productViewModel.setInitialState()
                    }
                )
            }
        ) { paddingValues -> paddingValues }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = DodgerBlue,
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun ShowProducts(products: List<Product>?, navController: NavController) {
    if (products.isNullOrEmpty()) {
        SearchNotFound()
    } else {
        LazyColumn {
            itemsIndexed(items = products) { position, item ->
                ProductCard(position, product = item, navController)
            }
        }
    }
}

@Composable
fun SearchNotFound() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .size(100.dp),
            imageVector = Icons.Outlined.Info,
            contentDescription = "Notifications",
            tint = Color.LightGray
        )
        Text(stringResource(id = R.string.product_screen_results_not_found))
    }
}

@Composable
fun ShowError(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .size(100.dp),
            imageVector = Icons.Outlined.Close,
            contentDescription = "Notifications",
            tint = Color.LightGray
        )
        Text(message)
    }
}

package com.test.melitest.presentation.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.test.melitest.R
import com.test.melitest.core.network.IMAGE_CATEGORY_BIG
import com.test.melitest.core.network.IMAGE_URL
import com.test.melitest.domain.products.models.Product
import com.test.melitest.presentation.products.ProductsStates
import com.test.melitest.presentation.products.ProductsViewModel
import com.test.melitest.presentation.theme.DodgerBlue
import com.test.melitest.presentation.theme.Selago
import com.test.melitest.presentation.theme.Turbo

@Composable
fun ProductDetailsScreen(
    productsViewModel: ProductsViewModel,
    position: Int?
) {
    val product = when (productsViewModel.mutableProductsStates.value) {
        is ProductsStates.ProductsData -> (productsViewModel.mutableProductsStates.value as ProductsStates.ProductsData).products?.get(
            position!!
        )

        else -> null
    }
    Scaffold(
        topBar = {
            ProductDetailsTopBar()
        }
    ) { padding ->
        padding
        BodyContent(product = product)
    }
}

@Composable
fun ProductDetailsTopBar() {
    TopAppBar(
        modifier = Modifier.wrapContentSize(),
        backgroundColor = Turbo,
        title = { },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "ShoppingCart")
            }
        }
    )
}

@Composable
fun BodyContent(product: Product?) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SentTo()
        Text(text = "${product?.condition}", modifier = Modifier.padding(start = 16.dp, top = 8.dp))
        Text(text = "${product?.title}", modifier = Modifier.padding(16.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("$IMAGE_URL${product?.thumbnail_id}$IMAGE_CATEGORY_BIG")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Text(
            text = "$ ${product?.price}",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        GenericButton(
            text = stringResource(id = R.string.product_details_screen_buy_now),
            backgroundColor = DodgerBlue,
            contentColor = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        GenericButton(
            text = stringResource(id = R.string.product_details_screen_add_to_cart),
            backgroundColor = Selago,
            contentColor = DodgerBlue
        )
        FavoritesAndShare()
    }
}

@Composable
private fun SentTo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(45.dp)
            .background(color = Turbo),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Place,
            contentDescription = "Place",
            modifier = Modifier.padding(start = 8.dp),
            tint = Color.Black
        )
        Text(text = "Enviar a Eduard Gomez", color = Color.Black)
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "KeyboardArrowRight",
            modifier = Modifier.padding(end = 8.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun GenericButton(text: String, backgroundColor: Color, contentColor: Color) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FavoritesAndShare() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(45.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Place",
            modifier = Modifier.padding(8.dp),
            tint = DodgerBlue
        )
        Text(
            text = stringResource(id = R.string.product_details_screen_add_to_favorites),
            color = DodgerBlue
        )
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = "Place",
            modifier = Modifier.padding(8.dp),
            tint = DodgerBlue
        )
        Text(text = stringResource(id = R.string.product_details_screen_share), color = DodgerBlue)
    }
}
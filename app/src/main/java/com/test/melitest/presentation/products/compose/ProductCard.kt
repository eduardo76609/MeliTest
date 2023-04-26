package com.test.melitest.presentation.products.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.test.melitest.domain.products.models.Product
import coil.request.ImageRequest
import com.test.melitest.core.network.IMAGE_CATEGORY_SMALL
import com.test.melitest.core.network.IMAGE_URL
import com.test.melitest.presentation.navigation.AppScreens

@Composable
fun ProductCard(position: Int, product: Product, navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .clickable(onClick = { navController.navigate(route = AppScreens.ProductDetailsScreen.route + "/$position") }),
        elevation = 10.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$IMAGE_URL${product?.thumbnail_id}$IMAGE_CATEGORY_SMALL")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = product.title.trim())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$ ${product.price}", fontWeight = FontWeight.Bold)
            }
        }
    }
}
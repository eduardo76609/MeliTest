package com.test.melitest.presentation.search_products

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.test.melitest.R
import com.test.melitest.presentation.theme.DodgerBlue

@Composable
fun SearchProductsScreen(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    goBack: () -> Unit,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(
                onClick = { goBack() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            }
        },
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_products_screen_search_on_meli),
                color = Color.Gray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = DodgerBlue,
            textColor = Color.Gray
        ),
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { onTextChange("") })
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Gray
                    )
                }
            }
        }
    )
}

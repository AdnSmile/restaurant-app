package com.vvwxx.bangkit.restaurantapp.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vvwxx.bangkit.restaurantapp.ui.theme.RestaurantAppTheme

@Composable
fun FavoriteIconButton(
    favorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        val iconButton = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        Icon(
            imageVector = iconButton,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteIconButtonPreview() {
    RestaurantAppTheme {
        FavoriteIconButton(
            false,
            onClick = {}
        )
    }
}
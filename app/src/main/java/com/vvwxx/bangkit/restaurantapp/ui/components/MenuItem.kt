package com.vvwxx.bangkit.restaurantapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvwxx.bangkit.restaurantapp.R
import com.vvwxx.bangkit.restaurantapp.ui.theme.RestaurantAppTheme

@Composable
fun MenuItem(
    jenis: String,
    menu: String,
    modifier: Modifier = Modifier,
) {

    val img = if (jenis == "foods") painterResource(R.drawable.food) else painterResource(R.drawable.drink3)

    Card(
        modifier = modifier.width(110.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(90.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
               text = menu,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MenuItemPreview() {
    RestaurantAppTheme {
        MenuItem(
            jenis = "foods",
            menu = "Sosis squash dan mint"
        )
    }
}
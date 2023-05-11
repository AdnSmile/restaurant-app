package com.vvwxx.bangkit.restaurantapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.theme.RestaurantAppTheme
import com.vvwxx.bangkit.restaurantapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RestaurantItem(
    resto: RestaurantsItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(360.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            GlideImage(
                model = "https://restaurant-api.dicoding.dev/images/medium/${resto.pictureId}",
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)) {
                Text(
                   text = resto.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )

                    Text(
                        text = resto.rating.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Text(
                        text = resto.city,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.weight(2f).offset(x = 225.dp, y = (-24).dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantItemPreview() {
    RestaurantAppTheme {
        RestaurantItem(
            resto = RestaurantsItem(
                "17",
                "Medan",
                "Mantep banget",
                4.9,
                "Loram ipsum",
            )
        )
    }
}
package com.vvwxx.bangkit.restaurantapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vvwxx.bangkit.restaurantapp.data.remote.response.Restaurant
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.vvwxx.bangkit.restaurantapp.R
import com.vvwxx.bangkit.restaurantapp.di.Injection
import com.vvwxx.bangkit.restaurantapp.ui.ViewModelFactory
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import com.vvwxx.bangkit.restaurantapp.ui.components.MenuItem

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.detailRestaurant.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getDetailRestaurant(id)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.restaurant,
                    onBackClick = navigateBack,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailContent(
    resto: Restaurant,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
//    var isFavorite by rememberSaveable{ mutableStateOf(isFavorite) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                        .weight(1f, false)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(3f)
                        .padding(16.dp)
                ) {
                    Text(
                        text = resto.city,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = resto.address,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }

            Column(
                modifier = Modifier.padding(start = 26.dp, end = 26.dp, bottom = 26.dp)
            ) {
                GlideImage(
                    model = "https://restaurant-api.dicoding.dev/images/medium/${resto.pictureId}",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = resto.name,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier.padding(top = 8.dp)
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
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = stringResource(R.string.text_about),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                Text(
                    text = resto.description,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = stringResource(R.string.text_foods),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                // lazy row foods
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(resto.menus.foods) { data ->
                        MenuItem(jenis = "foods", menu = data.name)
                    }
                }

                Text(
                    text = stringResource(R.string.text_drinks),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                // lazy row drinks
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(resto.menus.drinks) { data ->
                        MenuItem(jenis = "drinks", menu = data.name)
                    }
                }
            }
        }
    }
}
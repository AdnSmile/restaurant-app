package com.vvwxx.bangkit.restaurantapp.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.bangkit.restaurantapp.data.local.RestaurantEntity
import com.vvwxx.bangkit.restaurantapp.di.Injection
import com.vvwxx.bangkit.restaurantapp.ui.ViewModelFactory
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import com.vvwxx.bangkit.restaurantapp.ui.components.EmptyDataItem
import com.vvwxx.bangkit.restaurantapp.ui.components.RestaurantItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    val viewModel : FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    )

    viewModel.listFavorite.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {

            is UiState.Success -> {

                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (uiState.data.isEmpty()) EmptyDataItem(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp))
                    else {
                        FavoriteContent(
                            listResto = uiState.data,
                            navigateToDetail = navigateToDetail
                        )
                    }
                }

            }
            else -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listResto: List<RestaurantEntity>,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listResto, key =  { it.id }) { data ->
            RestaurantItem(
                data.pictureId,
                data.name,
                data.city,
                data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}
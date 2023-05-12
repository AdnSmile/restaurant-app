package com.vvwxx.bangkit.restaurantapp.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.components.RestaurantItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.bangkit.restaurantapp.di.Injection
import com.vvwxx.bangkit.restaurantapp.ui.ViewModelFactory
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import com.vvwxx.bangkit.restaurantapp.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {

    val query by viewModel.query

    viewModel.listRestaurant.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSearchResponse(query)
            }

            is UiState.Success -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchBar(
                        query = query,
                        onQueryChanges = viewModel::getSearchResponse,
                        modifier = Modifier.background(color = MaterialTheme.colors.primary)
                    )

                    HomeContent(
                        listResto = uiState.data,
                        navigateToDetail = navigateToDetail
                    )
                }
            }

            is UiState.Error -> {}
        }
    }
}
@Composable
fun HomeContent(
    listResto: List<RestaurantsItem>,
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

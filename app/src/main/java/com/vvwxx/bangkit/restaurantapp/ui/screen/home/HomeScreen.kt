package com.vvwxx.bangkit.restaurantapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.components.RestaurantItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.bangkit.restaurantapp.di.Injection
import com.vvwxx.bangkit.restaurantapp.ui.ViewModelFactory
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    viewModel.listRestaurant.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRestaurant()
            }

            is UiState.Success -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
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
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listResto) { data ->
            RestaurantItem(
                data,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}

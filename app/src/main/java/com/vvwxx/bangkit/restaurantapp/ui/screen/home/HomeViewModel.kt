package com.vvwxx.bangkit.restaurantapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo : RestaurantRepository
) : ViewModel() {

    val listRestaurant: StateFlow<UiState<List<RestaurantsItem>>> get() = repo.listRestaurant

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllRestaurant() {
        viewModelScope.launch {
            repo.getAllRestaurant()
        }
    }

    fun getSearchResponse(query: String) {
        _query.value = query
        viewModelScope.launch {
            repo.getSearchResponse(query)
        }
    }

}
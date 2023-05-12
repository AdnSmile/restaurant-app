package com.vvwxx.bangkit.restaurantapp.ui.screen.detail

import androidx.lifecycle.*
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.local.RestaurantEntity
import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo : RestaurantRepository
) : ViewModel() {
    val detailRestaurant: StateFlow<UiState<DetailRestaurantResponse>> = repo.detailRestaurant


    fun getDetailRestaurant(id: String) {
        viewModelScope.launch{
            repo.getDetailRestaurant(id)
        }
    }
}
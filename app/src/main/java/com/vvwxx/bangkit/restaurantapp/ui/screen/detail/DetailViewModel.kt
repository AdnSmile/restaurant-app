package com.vvwxx.bangkit.restaurantapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
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
package com.vvwxx.bangkit.restaurantapp.data

import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiService
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantRepository(
    private val apiService: ApiService,
) {
    private val _listRestaurant = MutableStateFlow<UiState<List<RestaurantsItem>>>(UiState.Loading)
    val listRestaurant: StateFlow<UiState<List<RestaurantsItem>>> get() = _listRestaurant

    private val _detailRestaurant = MutableStateFlow<UiState<DetailRestaurantResponse>>(UiState.Loading)
    val detailRestaurant: StateFlow<UiState<DetailRestaurantResponse>> get() = _detailRestaurant

    suspend fun getAllRestaurant() {
        _listRestaurant.value = UiState.Loading
        try {
            val response = apiService.getRestaurant()
            val restaurant = response.restaurants
            _listRestaurant.value = UiState.Success(restaurant)
        } catch (e: Exception) {
            _listRestaurant.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getDetailRestaurant(id: String) {
        _detailRestaurant.value = UiState.Loading
        try {
            val response = apiService.getDetailRestaurant(id)
            _detailRestaurant.value = UiState.Success(response)
        } catch (e: Exception) {
            _detailRestaurant.value = UiState.Error(e.message.toString())
        }
    }


    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): RestaurantRepository =
            instance ?: synchronized(this) {
                instance ?: RestaurantRepository(apiService)
            }.also { instance = it }
    }
}
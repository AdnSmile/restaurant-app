package com.vvwxx.bangkit.restaurantapp.data

import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.data.remote.response.SearchRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiService
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.*

class RestaurantRepository(
    private val apiService: ApiService,
) {
    private val _detailRestaurant = MutableStateFlow<UiState<DetailRestaurantResponse>>(UiState.Loading)
    val detailRestaurant: StateFlow<UiState<DetailRestaurantResponse>> get() = _detailRestaurant

    // ngambil data dari api, trus di ubah ke Flow
    private suspend fun getSearch(query: String) : Flow<SearchRestaurantResponse> {
        return flowOf(apiService.getSearchRestaurant(query))
    }

    // ngambil data Flow getSearch, namun cuma diambil RestaurantsItem aja
    suspend fun getSearchRestorant(query: String) : Flow<List<RestaurantsItem>> {
        return getSearch(query).map {
            it.restaurants
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
package com.vvwxx.bangkit.restaurantapp.data

import com.vvwxx.bangkit.restaurantapp.data.local.RestaurantDao
import com.vvwxx.bangkit.restaurantapp.data.local.RestaurantEntity
import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.data.remote.response.SearchRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiService
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.*

class RestaurantRepository(
    private val apiService: ApiService,
    private val restoDao: RestaurantDao,
) {
    private val _detailRestaurant = MutableStateFlow<UiState<DetailRestaurantResponse>>(UiState.Loading)
    val detailRestaurant: StateFlow<UiState<DetailRestaurantResponse>> get() = _detailRestaurant

    private val _listRestaurant = MutableStateFlow<UiState<List<RestaurantsItem>>>(UiState.Loading)
    val listRestaurant: StateFlow<UiState<List<RestaurantsItem>>> get() = _listRestaurant

    // ngambil data dari api, trus di ubah ke Flow
    private suspend fun getSearch(query: String) : Flow<SearchRestaurantResponse> {
        return flowOf(apiService.getSearchRestaurant(query))
    }

    // ngambil data Flow getSearch, namun cuma diambil RestaurantsItem aja
    private suspend fun getSearchRestorant(query: String) : Flow<List<RestaurantsItem>> {
        return getSearch(query).map {
            it.restaurants
        }
    }

    suspend fun getSearchResponse(query: String) {
        getSearchRestorant(query)
            .catch {
                _listRestaurant.value = UiState.Error(it.message.toString())
            }
            .collect { data ->
                _listRestaurant.value = UiState.Success(data)
            }
    }

    fun getAllFavoriteResto(): Flow<List<RestaurantEntity>> = restoDao.getFavoriteResto()

    suspend fun saveFavorite(resto: RestaurantEntity) {
        restoDao.saveFavoriteResto(resto)
    }

    suspend fun deleteFavorite(id: String) {
        restoDao.deleteResto(id)
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
            restoDao: RestaurantDao
        ): RestaurantRepository =
            instance ?: synchronized(this) {
                instance ?: RestaurantRepository(apiService, restoDao)
            }.also { instance = it }
    }
}
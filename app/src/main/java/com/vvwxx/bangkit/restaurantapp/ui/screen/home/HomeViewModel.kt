package com.vvwxx.bangkit.restaurantapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo : RestaurantRepository
) : ViewModel() {

    private val _listRestaurant = MutableStateFlow<UiState<List<RestaurantsItem>>>(UiState.Loading)
    val listRestaurant: StateFlow<UiState<List<RestaurantsItem>>> get() = _listRestaurant

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getSearch(query: String) {
        _query.value = query
        viewModelScope.launch {
            getSearchResponse(_query.value)
        }
    }

    private suspend fun getSearchResponse(query: String) {
        repo.getSearchRestorant(query)
            .catch {
                _listRestaurant.value = UiState.Error(it.message.toString())
            }
            .collect { data ->
                _listRestaurant.value = UiState.Success(data)
            }
    }
}
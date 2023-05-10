package com.vvwxx.bangkit.restaurantapp.ui.screen.home

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
//    private val _uiState: MutableStateFlow<UiState<List<RestaurantsItem>>> = MutableStateFlow(UiState.Loading)
//    val uiState: StateFlow<UiState<List<RestaurantsItem>>> get() = _uiState
//
//    fun getAllRestaurant() {
//        viewModelScope.launch {
//            repo.getAllRestaurant()
//                .catch{
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//        }
//    }
}
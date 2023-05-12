package com.vvwxx.bangkit.restaurantapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.local.RestaurantEntity
import com.vvwxx.bangkit.restaurantapp.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repo : RestaurantRepository
) : ViewModel() {

    private val _listFavorite: MutableStateFlow<UiState<List<RestaurantEntity>>> = MutableStateFlow(UiState.Loading)
    private val listFavorite: StateFlow<UiState<List<RestaurantEntity>>> get() = _listFavorite

    init {
        getAllFavoriteResto()
    }
    private fun getAllFavoriteResto(){
        viewModelScope.launch {
            _listFavorite.value = UiState.Loading
            repo.getAllFavoriteResto()
                .collect { resto ->
                    _listFavorite.value = UiState.Success(resto)
                }
        }
    }

    fun isFavorite(id: String): Flow<Boolean> = listFavorite.map { uiState ->
        when (uiState) {
            is UiState.Success -> {
                val favoriteList = uiState.data
                favoriteList.any { restaurantEntity ->
                    restaurantEntity.id == id
                }
            }
            else -> false
        }
    }

    fun saveFavoriteResto(resto: RestaurantEntity) {
        viewModelScope.launch {
            repo.saveFavorite(resto)
        }
    }

    fun deleteFavoriteResto(id: String) {
        viewModelScope.launch {
            repo.deleteFavorite(id)
        }
    }
}
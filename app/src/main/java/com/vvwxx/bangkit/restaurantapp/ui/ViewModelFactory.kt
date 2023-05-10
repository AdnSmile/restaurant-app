package com.vvwxx.bangkit.restaurantapp.ui

import androidx.lifecycle.ViewModel
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import androidx.lifecycle.ViewModelProvider
import com.vvwxx.bangkit.restaurantapp.ui.screen.detail.DetailViewModel
import com.vvwxx.bangkit.restaurantapp.ui.screen.favorite.FavoriteViewModel
import com.vvwxx.bangkit.restaurantapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repo : RestaurantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
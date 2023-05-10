package com.vvwxx.bangkit.restaurantapp.di

import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): RestaurantRepository {
        val apiService = ApiConfig.getApiService()
        return RestaurantRepository.getInstance(apiService)
    }
}
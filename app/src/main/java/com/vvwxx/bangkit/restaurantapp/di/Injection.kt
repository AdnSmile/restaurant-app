package com.vvwxx.bangkit.restaurantapp.di

import android.content.Context
import com.vvwxx.bangkit.restaurantapp.data.RestaurantRepository
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): RestaurantRepository {
        val apiService = ApiConfig.getApiService()
        return RestaurantRepository.getInstance(apiService)
    }
}
package com.vvwxx.bangkit.restaurantapp.data.remote.retrofit

import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantResponse
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getRestaurant(): RestaurantResponse
}
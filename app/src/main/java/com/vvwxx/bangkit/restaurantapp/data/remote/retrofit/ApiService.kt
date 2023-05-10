package com.vvwxx.bangkit.restaurantapp.data.remote.retrofit

import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("list")
    suspend fun getRestaurant(): RestaurantResponse

    @GET("detail/{id}")
    suspend fun getDetailRestaurant(
        @Path("id") id: String,
    ): DetailRestaurantResponse
}
package com.vvwxx.bangkit.restaurantapp.data.remote.retrofit

import com.vvwxx.bangkit.restaurantapp.data.remote.response.DetailRestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantResponse
import com.vvwxx.bangkit.restaurantapp.data.remote.response.SearchRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun getRestaurant(): RestaurantResponse

    @GET("detail/{id}")
    suspend fun getDetailRestaurant(
        @Path("id") id: String,
    ): DetailRestaurantResponse

    @GET("search")
    suspend fun getSearchRestaurant(@Query("q") query: String) : SearchRestaurantResponse
}
package com.vvwxx.bangkit.restaurantapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem
import com.vvwxx.bangkit.restaurantapp.data.remote.retrofit.ApiService

class RestaurantRepository(
    private val apiService: ApiService,
) {



//    fun getListRestaurant(): LiveData<Result<List<RestaurantsItem>>> = liveData {
//        emit(Result.Loading)
//
//        try {
//            val response = apiService.getRestaurant()
//            val listRestaurant = response.restaurants
//        }
//    }


    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): RestaurantRepository =
            instance ?: synchronized(this) {
                instance ?: RestaurantRepository(apiService)
            }.also { instance = it }
    }
}
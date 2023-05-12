package com.vvwxx.bangkit.restaurantapp.model

import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem

data class Restaurant(
    val restaurant: RestaurantsItem,
    val isFavorite: Boolean
)
package com.vvwxx.bangkit.restaurantapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchRestaurantResponse(

	@field:SerializedName("founded")
	val founded: Int,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItem>,

	@field:SerializedName("error")
	val error: Boolean
)
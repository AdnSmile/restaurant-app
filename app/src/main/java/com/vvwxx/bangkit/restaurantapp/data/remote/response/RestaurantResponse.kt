package com.vvwxx.bangkit.restaurantapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class RestaurantsItem(

	@field:SerializedName("pictureId")
	val pictureId: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String
)

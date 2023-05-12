package com.vvwxx.bangkit.restaurantapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantsItem(

	@field:SerializedName("pictureId")
	val pictureId: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("id")
	val id: String
)

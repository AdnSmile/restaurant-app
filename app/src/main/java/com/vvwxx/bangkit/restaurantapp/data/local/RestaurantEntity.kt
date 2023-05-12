package com.vvwxx.bangkit.restaurantapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "restaurant")
class RestaurantEntity (
    @field:ColumnInfo("pictureId")
    @field:PrimaryKey
    var pictureId: String,

    @field:ColumnInfo("city")
    var city: String,

    @field:ColumnInfo("name")
    var name: String,

    @field:ColumnInfo("rating")
    var rating: Double,

    @field:ColumnInfo("id")
    var id: String,

    @field:ColumnInfo("favorite")
    var isFavorite: Boolean
) : Parcelable

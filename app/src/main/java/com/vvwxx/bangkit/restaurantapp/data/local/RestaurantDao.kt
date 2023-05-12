package com.vvwxx.bangkit.restaurantapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getFavoriteResto(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavoriteResto(resto: RestaurantEntity)

    @Query("DELETE FROM restaurant WHERE id = :id")
    suspend fun deleteResto(id: String)
}
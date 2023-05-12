package com.vvwxx.bangkit.restaurantapp.data.local

import android.content.Context
import androidx.room.*
import com.vvwxx.bangkit.restaurantapp.data.remote.response.RestaurantsItem

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): RestaurantDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java, "resto_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
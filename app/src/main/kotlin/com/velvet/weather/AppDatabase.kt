package com.velvet.weather

import androidx.room.Database
import androidx.room.RoomDatabase
import com.velvet.weather.weather.data.CityDao
import com.velvet.weather.weather.data.schemas.City

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
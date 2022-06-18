package com.velvet.weather.weather.data

import androidx.room.*
import com.velvet.weather.weather.data.schemas.City

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cities: List<City>)

    @Delete
    fun deleteAll(cities: List<City>)

}
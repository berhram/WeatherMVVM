package com.velvet.weather.weather.data

import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.presentation.AddCity
import com.velvet.weather.weather.presentation.DeleteCity
import com.velvet.weather.weather.presentation.GetSavedCities

interface WeatherCacheDataSource : AddCity, DeleteCity, GetSavedCities {


    class Base(
        private val savedCities: SavedCities.Mutable
    ) : WeatherCacheDataSource {

        override fun addCity(city: City) {
            val list = savedCities.read().toMutableSet()
            list.add(city.toSaveString())
            savedCities.save(list)
        }

        override fun deleteCity(city: City) {
            savedCities.save(savedCities.read().readToList().filter { it != city }.toSaveSet())
        }

        override fun getCities(): List<City> {
            return savedCities.read().readToList()
        }
    }
}
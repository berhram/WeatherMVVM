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
            list.add(toSaveString(city))
            savedCities.save(list)
        }

        override fun deleteCity(city: City) {
            savedCities.save(toSaveSet(readToList(savedCities.read()).filter { it != city }))
        }

        override fun getCities(): List<City> {
            return readToList(savedCities.read())
        }

        private fun toSaveSet(cities: List<City>): Set<String> {
            val output = mutableSetOf<String>()
            cities.forEach {
                output.add(toSaveString(it))
            }
            return output
        }

        private fun toSaveString(city: City): String {
            return city.name + "|" + city.country + "|" + city.latitude + "|" + city.longitude
        }

        private fun readToList(cities: Set<String>): List<City> {
            val output = mutableListOf<City>()
            cities.forEach {
                it.split("|").apply {
                    output.add(
                        City(
                            name = this[0],
                            country = this[1],
                            latitude = this[2].toDouble(),
                            longitude = this[3].toDouble()
                        )
                    )
                }
            }
            return output
        }
    }
}
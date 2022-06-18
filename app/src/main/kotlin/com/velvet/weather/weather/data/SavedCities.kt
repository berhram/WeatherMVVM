package com.velvet.weather.weather.data

import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save
import com.velvet.weather.weather.data.schemas.City

class SavedCities {

    interface SaveCities : Save<List<City>>

    interface ReadCities : Read<List<City>>

    interface Mutable : SaveCities, ReadCities

    class Base(private val cityDao: CityDao) : Mutable {

        override fun read(): List<City> = cityDao.getAll()

        override fun save(data: List<City>) {
            cityDao.deleteAll(cityDao.getAll())
            cityDao.insertAll(cities = data)
        }
    }
}
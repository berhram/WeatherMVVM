package com.velvet.weather.weather.data

import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save
import com.github.johnnysc.coremvvm.data.PreferenceDataStore
import com.velvet.weather.weather.data.schemas.City

class SavedCities {

    interface SaveCities : Save<Set<String>>

    interface ReadCities : Read<Set<String>>

    interface Mutable : SaveCities, ReadCities

    class Base(private val preferences: PreferenceDataStore) : Mutable {

        override fun save(data: Set<String>) = preferences.save(KEY, data)

        override fun read() = preferences.read(KEY)

        companion object {
            private const val KEY = "FavoriteCurrenciesKey"
        }
    }
}

fun List<City>.toSaveSet(): Set<String> {
    val output = mutableSetOf<String>()
    this.forEach {
        output.add(it.toSaveString())
    }
    return output
}

fun City.toSaveString(): String {
    return this.name + "|" + this.country + "|" + this.latitude + "|" + this.longitude
}

fun Set<String>.readToList(): List<City> {
    val output = mutableListOf<City>()
    this.forEach {
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
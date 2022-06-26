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


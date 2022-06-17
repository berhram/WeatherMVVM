package com.velvet.weather.weather.domain

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.velvet.weather.weather.WeatherUi
import com.velvet.weather.weather.presentation.CityUi
import com.velvet.weather.weather.presentation.DeleteCity
import com.velvet.weather.weather.presentation.LastUpdateUi

interface WeatherDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val date: String,
        private val list: List<Pair<String, Double>>
    ) : WeatherDomain {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(date = date, list = list)
    }

    interface Mapper<T> {
        fun map(date: String, list: List<Pair<String, Double>>): T

        class Base(
            private val deleteCity: DeleteCity
        ) : Mapper<WeatherUi> {

            override fun map(date: String, list: List<Pair<String, Double>>): WeatherUi {
                val output = mutableListOf<ItemUi>(LastUpdateUi("Last update: $date"))
                output.addAll(
                    list.map {
                        CityUi(
                            name = it.first,
                            temperature = "${it.second}\u00B0C",
                            deleteCity = deleteCity
                        )
                    }
                )
                return WeatherUi.Base(output)
            }
        }

    }
}
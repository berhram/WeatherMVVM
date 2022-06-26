package com.velvet.weather.weather.domain

import android.annotation.SuppressLint
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.velvet.weather.weather.presentation.WeatherUi
import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.presentation.CityUi
import com.velvet.weather.weather.presentation.DeleteCity
import com.velvet.weather.weather.presentation.LastUpdateUi
import java.text.SimpleDateFormat
import java.util.*

interface WeatherDomain {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val date: Long?,
        private val list: List<Pair<City, Double?>>
    ) : WeatherDomain {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(date = date, list = list)
    }

    interface Mapper<T> {
        fun map(date: Long?, list: List<Pair<City, Double?>>): T

        class Base(
            private val deleteCity: DeleteCity
        ) : Mapper<WeatherUi> {

            @SuppressLint("SimpleDateFormat")
            override fun map(date: Long?, list: List<Pair<City, Double?>>): WeatherUi {
                val formattedDate = if (date != null) {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(date * 1000))
                } else {
                    "no data for some reason"
                }
                val output = mutableListOf<ItemUi>(LastUpdateUi("Last update: $formattedDate"))
                output.addAll(
                    list.map {
                        CityUi(
                            city = it.first,
                            temperature = "${it.second ?: "-"}\u00B0C",
                            deleteCity = deleteCity
                        )
                    }
                )
                return WeatherUi.Base(output)
            }
        }

    }
}
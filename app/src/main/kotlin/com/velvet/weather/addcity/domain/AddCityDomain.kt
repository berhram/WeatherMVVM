package com.velvet.weather.addcity.domain

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.velvet.weather.addcity.presentation.AddCityUi
import com.velvet.weather.addcity.presentation.SuggestionUi
import com.velvet.weather.weather.data.schemas.City
import com.velvet.weather.weather.presentation.*

interface AddCityDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val list: List<City>
    ) : AddCityDomain {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(list = list)
    }

    interface Mapper<T> {
        fun map(list: List<City>): T

        class Base(
            private val addCity: AddCity
        ) : Mapper<AddCityUi> {

            override fun map(list: List<City>): AddCityUi {
                val output = mutableListOf<ItemUi>()
                output.addAll(
                    list.map {
                        SuggestionUi(
                            "${it.name}, ${it.country}",
                            city = it,
                            addCity = addCity
                        )
                    }
                )
                return AddCityUi.Base(output)
            }
        }

    }
}
package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView
import com.velvet.weather.weather.UiTypes
import com.velvet.weather.weather.data.schemas.City

class CityUi(
    private val city: City,
    private val temperature: String,
    private val deleteCity: DeleteCity
) : ItemUi {

    override fun type(): Int = UiTypes.CITY.value

    override fun show(vararg views: MyView) {
        views[0].show(city.name)
        views[1].show(temperature)
        views[2].handleClick {
            deleteCity.deleteCity(city)
        }
    }

    override fun id(): String = city.name

    override fun content(): String = temperature
}
package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView
import com.velvet.weather.weather.UiTypes

class CityUi(
    private val name: String,
    private val temperature: String,
    private val deleteCity: DeleteCity
) : ItemUi {

    override fun type(): Int = UiTypes.CITY.value

    override fun show(vararg views: MyView) {
        views[0].show(name)
        views[1].show(temperature)
        views[2].handleClick {
            deleteCity.deleteCity(name)
        }
    }

    override fun id(): String = name

    override fun content(): String = temperature
}

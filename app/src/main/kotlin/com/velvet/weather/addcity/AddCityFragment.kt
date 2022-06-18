package com.velvet.weather.addcity

import com.github.johnnysc.coremvvm.presentation.BackPress
import com.velvet.weather.R

class AddCityFragment : BackPress.Fragment<AddCityUi, AddCityViewModel>() {

    override val layoutResId: Int = R.layout.fragment_add_city
}
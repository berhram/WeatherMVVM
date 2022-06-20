package com.velvet.weather.addcity.presentation

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class AddCityNavigationScreen : NavigationScreen(
    id = "AddPlaceNavigationScreen",
    clasz = AddCityFragment::class.java,
    strategy = ShowStrategy.REPLACE
)
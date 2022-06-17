package com.velvet.weather.weather

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class WeatherNavigationScreen : NavigationScreen(
    id = "OverviewNavigationScreen",
    WeatherFragment::class.java,
    ShowStrategy.REPLACE
)
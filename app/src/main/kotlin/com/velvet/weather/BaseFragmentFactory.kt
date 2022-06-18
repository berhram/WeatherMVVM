package com.velvet.weather

import androidx.fragment.app.FragmentManager
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.velvet.weather.addcity.AddCityNavigationScreen
import com.velvet.weather.weather.presentation.WeatherNavigationScreen

class BaseFragmentFactory(
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
) {

    override val screens: List<NavigationScreen> = listOf(
        WeatherNavigationScreen(),
        AddCityNavigationScreen()
    )
}
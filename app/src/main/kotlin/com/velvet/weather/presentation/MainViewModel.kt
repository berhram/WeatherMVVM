package com.velvet.weather.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.velvet.weather.addcity.presentation.AddCityNavigationScreen
import com.velvet.weather.weather.presentation.WeatherNavigationScreen

class MainViewModel(
    canGoBack: CanGoBack,
    private val navigationCommunication: NavigationCommunication.Mutable,
    private val progressCommunication: ProgressCommunication.Mutable,
    dispatchers: Dispatchers,
    communication: GlobalErrorCommunication.Mutable
) : BackPress.Activity.ViewModel<String>(
    canGoBack,
    communication,
    dispatchers
) {
    private val weatherNavigationScreen = WeatherNavigationScreen()
    private val addPlaceNavigationScreen = AddCityNavigationScreen()

    init {
        chooseTab(0)
    }

    fun chooseTab(tabPosition: Int) = navigationCommunication.map(
        if (tabPosition == 0) weatherNavigationScreen else addPlaceNavigationScreen
    )

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) {
        progressCommunication.observe(owner, observer)
    }
}
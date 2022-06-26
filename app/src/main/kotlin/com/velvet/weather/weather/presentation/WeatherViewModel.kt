package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.velvet.weather.addcity.presentation.AddCityNavigationScreen
import com.velvet.weather.weather.domain.WeatherInteractor

class WeatherViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: WeatherInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    communication: WeatherCommunication,
    dispatchers: Dispatchers
) : BackPress.ViewModel<WeatherUi>(canGoBackCallback, communication, dispatchers) {

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }

    private var canGoBack = true

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.savedCities({ refresh() }) { communication.map(it) }
        }
    }

    fun getSaved() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.savedCities(atFinish) { communication.map(it) }
        }
    }

    fun refresh() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.refresh(atFinish) { communication.map(it) }
        }
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)
}
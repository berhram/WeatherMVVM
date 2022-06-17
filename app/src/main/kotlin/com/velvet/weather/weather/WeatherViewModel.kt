package com.velvet.weather.weather

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.velvet.weather.weather.domain.WeatherInteractor

class WeatherViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: WeatherInteractor,
    progressCommunication: ProgressCommunication.Update,
    communication: OverviewCommunication,
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
            interactor.currencies(atFinish) { communication.map(it) }
        }
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)
}
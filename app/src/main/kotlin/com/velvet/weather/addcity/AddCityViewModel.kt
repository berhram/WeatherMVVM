package com.velvet.weather.addcity

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.velvet.weather.addcity.presentation.AddCityCommunication
import com.velvet.weather.addcity.presentation.AddCityUi

class AddCityViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: AddCityInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    communication: AddCityCommunication,
    dispatchers: Dispatchers
) : BackPress.ViewModel<AddCityUi>(
    canGoBackCallback = canGoBackCallback,
    communication = communication,
    dispatchers = dispatchers
) {

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

        }
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)
}
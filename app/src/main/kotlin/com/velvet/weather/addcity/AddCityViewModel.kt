package com.velvet.weather.addcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.velvet.weather.addcity.domain.AddCityInteractor
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

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

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

    }

    fun searchLocations() {
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getSuggestions(
                atFinish = atFinish,
                keyword = search.value ?: "",
                successful = {
                    communication.map(it)
                })
        }
    }

    fun input(search: String) {
        _search.value = search
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)
}
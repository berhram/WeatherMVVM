package com.velvet.weather.addcity.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.velvet.weather.addcity.domain.AddCityInteractor

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

    private val search = MutableLiveData<String>()

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }

    private var canGoBack = true

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    fun searchLocations() {
        progressCommunication.map(Visibility.Visible())
        canGoBack = false
        handle {
            interactor.getSuggestions(
                atFinish = atFinish,
                keyword = search.value ?: "",
                successful = {
                    Log.d("TEST", "searchLocations succ")
                    communication.map(it)
                })
        }
    }

    fun input(search: String) {
        this.search.value = search.trim()
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)
}
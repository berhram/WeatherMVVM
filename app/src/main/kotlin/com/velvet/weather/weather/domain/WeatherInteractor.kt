package com.velvet.weather.weather.domain

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor
import com.velvet.weather.weather.presentation.WeatherUi
import com.velvet.weather.weather.data.WeatherRepository

interface WeatherInteractor {

    suspend fun savedCities(
        atFinish: () -> Unit,
        successful: (WeatherUi) -> Unit
    )

    suspend fun refresh(
        atFinish: () -> Unit,
        successful: (WeatherUi) -> Unit
    )

    class Base(
        private val mapper: WeatherDomain.Mapper<WeatherUi>,
        private val repository: WeatherRepository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), WeatherInteractor {

        override suspend fun savedCities(
            atFinish: () -> Unit,
            successful: (WeatherUi) -> Unit,
        ) = handle(successful, atFinish) {
            return@handle repository.getSaved().map(mapper)
        }

        override suspend fun refresh(atFinish: () -> Unit, successful: (WeatherUi) -> Unit) =
            handle(successful, atFinish) {
                return@handle repository.updateWeather().map(mapper)
            }
    }
}
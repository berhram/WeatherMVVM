package com.velvet.weather.weather.domain

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor
import com.velvet.weather.weather.WeatherUi
import com.velvet.weather.weather.data.WeatherRepository

interface WeatherInteractor {

    suspend fun currencies(
        atFinish: () -> Unit,
        successful: (WeatherUi) -> Unit
    )

    class Base(
        private val mapper: WeatherDomain.Mapper<WeatherUi>,
        private val repository: WeatherRepository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), WeatherInteractor {

        override suspend fun currencies(
            atFinish: () -> Unit,
            successful: (WeatherUi) -> Unit,
        ) = handle(successful, atFinish) {
            val data = repository.cities()
            return@handle data.map(mapper)
        }
    }
}
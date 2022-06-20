package com.velvet.weather.addcity.domain

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor
import com.velvet.weather.addcity.data.AddCityRepository
import com.velvet.weather.addcity.presentation.AddCityUi

interface AddCityInteractor {

    suspend fun getSuggestions(
        atFinish: () -> Unit,
        successful: (AddCityUi) -> Unit,
        keyword: String
    )

    class Base(
        private val mapper: AddCityDomain.Mapper<AddCityUi>,
        private val repository: AddCityRepository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), AddCityInteractor {

        override suspend fun getSuggestions(
            atFinish: () -> Unit,
            successful: (AddCityUi) -> Unit,
            keyword: String
        ) = handle(successful, atFinish) {
            return@handle repository.getSuggestions(keyword).map(mapper)
        }

    }
}
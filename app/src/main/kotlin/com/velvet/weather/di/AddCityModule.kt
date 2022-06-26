package com.velvet.weather.di

import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.presentation.HandleUiError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.velvet.weather.addcity.data.BaseAddCityRepository
import com.velvet.weather.addcity.data.GeoCloudDataSource
import com.velvet.weather.addcity.data.ProvideGeoService
import com.velvet.weather.addcity.domain.AddCityDomain
import com.velvet.weather.addcity.domain.AddCityInteractor
import com.velvet.weather.addcity.presentation.AddCityCommunication
import com.velvet.weather.addcity.presentation.AddCityViewModel
import com.velvet.weather.weather.data.*

class AddCityModule(
    private val coreModule: CoreModule,
    private val cacheDataSource: WeatherCacheDataSource
) : Module<AddCityViewModel> {

    override fun viewModel(): AddCityViewModel {
        val repository = BaseAddCityRepository(
            cacheDataSource = cacheDataSource,
            cloudDataSource = GeoCloudDataSource.Base(
                geoService = ProvideGeoService.Base(coreModule).getService(),
                handleError = HandleDomainError()
            )
        )
        return AddCityViewModel(
            canGoBackCallback = coreModule.provideCanGoBack(),
            interactor = AddCityInteractor.Base(
                mapper = AddCityDomain.Mapper.Base(
                    repository
                ),
                repository = repository,
                dispatchers = coreModule.dispatchers(),
                handleError = HandleUiError(
                    manageResources = coreModule,
                    globalErrorCommunication = coreModule.provideGlobalErrorCommunication()
                )
            ),
            progressCommunication = coreModule.provideProgressCommunication(),
            communication = AddCityCommunication.Base(),
            dispatchers = coreModule.dispatchers()
        )
    }
}
package com.velvet.weather.di

import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.presentation.HandleUiError
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.velvet.weather.weather.data.*
import com.velvet.weather.weather.domain.WeatherDomain
import com.velvet.weather.weather.domain.WeatherInteractor
import com.velvet.weather.weather.presentation.WeatherCommunication
import com.velvet.weather.weather.presentation.WeatherViewModel

class WeatherModule(
    private val coreModule: CoreModule,
    private val cacheDataSource: WeatherCacheDataSource,
) : Module<WeatherViewModel> {

    override fun viewModel(): WeatherViewModel {
        val repository = BaseWeatherRepository(
            cacheDataSource = cacheDataSource,
            cloudDataSource = WeatherCloudDataSource.Base(
                weatherService = ProvideWeatherService.Base(coreModule).getService(),
                handleError = HandleDomainError()
            )
        )
        return WeatherViewModel(
            canGoBackCallback = coreModule.provideCanGoBack(),
            interactor = WeatherInteractor.Base(
                mapper = WeatherDomain.Mapper.Base(
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
            communication = WeatherCommunication.Base(),
            dispatchers = coreModule.dispatchers(),
        )
    }
}
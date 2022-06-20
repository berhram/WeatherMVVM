package com.velvet.weather

import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class MainModule(
    private val coreModule: CoreModule,
    private val navigationCommunication: NavigationCommunication.Mutable
) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        coreModule.provideCanGoBack(),
        navigationCommunication = navigationCommunication,
        coreModule.provideProgressCommunication(),
        coreModule.dispatchers(),
        coreModule.provideGlobalErrorCommunication()
    )
}
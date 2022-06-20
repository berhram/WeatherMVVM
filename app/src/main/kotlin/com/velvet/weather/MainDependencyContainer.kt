package com.velvet.weather

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule,
    private val navigationCommunication: NavigationCommunication.Mutable
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
        if (clazz == MainViewModel::class.java)
            MainModule(coreModule, navigationCommunication)
        else
            dependencyContainer.module(clazz)
}

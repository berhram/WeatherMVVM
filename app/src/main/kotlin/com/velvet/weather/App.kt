package com.velvet.weather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.coremvvm.sl.ViewModelsFactory

class App : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        val coreModule = CoreModule.Base(this)
        val navigationCommunication = NavigationCommunication.Base()
        val main = MainDependencyContainer(DependencyContainer.Error(), coreModule, navigationCommunication)
        viewModelsFactory = ViewModelsFactory(
            FeaturesDependencyContainer(
                context = this,
                navigationCommunication = navigationCommunication,
                coreModule = coreModule,
                dependencyContainer = main
            )
        )
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}
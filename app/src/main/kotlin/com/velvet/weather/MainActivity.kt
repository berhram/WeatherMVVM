package com.velvet.weather

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.sl.ProvideViewModel

class MainActivity : BackPress.Activity<MainViewModel>(), ProvideViewModel {

    private lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentFactory = BaseFragmentFactory(R.id.container, supportFragmentManager)
        val progress = findViewById<View>(R.id.progress)

        provideViewModel(MainViewModel::class.java, this).apply {
            observeNavigation(this@MainActivity) { navScreen ->
                fragmentFactory.fragment(navScreen)
            }
            observeProgress(this@MainActivity) { isVisible ->
                isVisible.apply(progress)
            }
        }

    }

    override fun <T : androidx.lifecycle.ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).provideViewModel(clazz, owner)
}
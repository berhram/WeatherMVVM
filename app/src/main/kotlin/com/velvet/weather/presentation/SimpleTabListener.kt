package com.velvet.weather.presentation

import com.google.android.material.tabs.TabLayout

abstract class SimpleTabListener : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab) = Unit
    override fun onTabUnselected(tab: TabLayout.Tab) = Unit
    override fun onTabReselected(tab: TabLayout.Tab) = onTabSelected(tab)
}
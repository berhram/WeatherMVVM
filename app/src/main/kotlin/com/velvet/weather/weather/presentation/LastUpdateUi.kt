package com.velvet.weather.weather.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class LastUpdateUi(
    private val date: String
) : ItemUi {

    override fun content(): String = date

    override fun id(): String = date

    override fun show(vararg views: MyView) = views[0].show(date)

    override fun type(): Int = UiTypes.LAST_UPDATE.value
}
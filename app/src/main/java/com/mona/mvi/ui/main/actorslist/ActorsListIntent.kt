package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.interfaces.BaseIntent

sealed class ActorsListIntent: BaseIntent<ActorsListAction> {
    object InitialIntent : ActorsListIntent()
    data class RefreshIntent(val forceUpdate: Boolean) : ActorsListIntent()

    override fun mapToAction(): ActorsListAction =
        when (this){
            is InitialIntent -> ActorsListAction.LoadActorsAction(true)
            is RefreshIntent -> ActorsListAction.LoadActorsAction(this.forceUpdate)
        }
}

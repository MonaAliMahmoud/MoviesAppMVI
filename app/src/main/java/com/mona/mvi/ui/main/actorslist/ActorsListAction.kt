package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.interfaces.BaseAction

sealed class ActorsListAction: BaseAction {
    data class LoadActorsAction(val forceUpdate: Boolean) : ActorsListAction()
}

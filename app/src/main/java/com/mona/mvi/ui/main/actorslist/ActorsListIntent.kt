package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.interfaces.BaseIntent

sealed class ActorsListIntent: BaseIntent {
    object InitialIntent : ActorsListIntent()
}

package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.data.model.ActorInfo
import com.mona.mvi.ui.base.interfaces.BaseViewState

data class ActorsListViewState (
    val isLoading: Boolean,
    val actors: ArrayList<ActorInfo>,
    val error: Throwable?
            ): BaseViewState{

    companion object {
        fun idle(): ActorsListViewState {
            return ActorsListViewState(
                isLoading = false,
                actors = ArrayList(),
                error = null
            )
        }
    }
}

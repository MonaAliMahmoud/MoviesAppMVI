package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.data.model.ActorInfo
import com.mona.mvi.ui.base.interfaces.BaseResult

sealed class ActorsListResult: BaseResult {
    sealed class LoadActorsResult : ActorsListResult() {
        data class Success(val result: ArrayList<ActorInfo>): LoadActorsResult()
        data class Failure(val error: Throwable): LoadActorsResult()
        object InFlight : LoadActorsResult()
    }
}
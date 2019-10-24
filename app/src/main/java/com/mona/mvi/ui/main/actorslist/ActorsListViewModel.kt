package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.BaseViewModel
import com.mona.mvi.utils.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(actionProcessor: ActorsListActionProcessor)
    :BaseViewModel<ActorsListAction, ActorsListIntent,
                ActorsListViewState, ActorsListResult>(actionProcessor) {

    override fun getIntentFilters(): ObservableTransformer<ActorsListIntent, ActorsListIntent> =
        ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(ActorsListIntent.InitialIntent::class.java).take(1),
                    shared.notOfType(ActorsListIntent.InitialIntent::class.java)
                )
            }
        }

    override fun getIdleState() = ActorsListViewState.idle()

    override fun getReducer(): BiFunction<ActorsListViewState, in ActorsListResult, ActorsListViewState>? =
        reducer

    companion object {
        private val reducer =
            BiFunction { previousState: ActorsListViewState, result: ActorsListResult ->

                        when (result) {
                        is ActorsListResult.LoadActorsResult.Success -> {
                            previousState.copy(
                                isLoading = false,
                                actors = result.result
                            )
                        }
                        is ActorsListResult.LoadActorsResult.Failure -> previousState.copy(
                            isLoading = false,
                            error = result.error
                        )
                        is ActorsListResult.LoadActorsResult.InFlight -> previousState.copy(
                            isLoading = true
                        )
                    }

            }
    }
}
package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.interfaces.MviActionProcessor
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorsListActionProcessor @Inject constructor(repository: ActorsListRepository):
    MviActionProcessor<ActorsListAction, ActorsListResult> {

    private val loadActorsProcessor =
        ObservableTransformer<ActorsListAction, ActorsListResult> { actions ->
            actions.flatMap { action ->
                repository.getActorsData()
                    .toObservable()
                    .map { actors -> ActorsListResult.LoadActorsResult.Success(actors.results!!) }
                    .cast(ActorsListResult.LoadActorsResult::class.java)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(ActorsListResult.LoadActorsResult.InFlight)
            }
        }

    override var actionProcessor = ObservableTransformer<ActorsListAction, ActorsListResult>
    { actions ->
        actions.publish { shared ->
            shared.ofType(ActorsListAction.LoadActorsAction::class.java).compose(
                loadActorsProcessor
            )
                .mergeWith(
                    shared.filter { v ->
                        v !is ActorsListAction.LoadActorsAction
                    }.flatMap { w ->
                        Observable.error<ActorsListResult>(
                            IllegalArgumentException("Unknown Action type: $w")
                        )
                    }
                )
        }
    }
}
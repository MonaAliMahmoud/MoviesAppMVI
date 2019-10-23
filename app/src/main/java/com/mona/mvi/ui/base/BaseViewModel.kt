package com.mona.mvi.ui.base

import androidx.lifecycle.ViewModel
import com.mona.mvi.ui.base.interfaces.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<A:BaseAction, I: BaseIntent<A>, S: BaseViewState, R: BaseResult>
        (val actionProcessor: MviActionProcessor<A,R>): ViewModel(),  MviViewModel<A, I, S>  {

    private val intentsSubject: PublishSubject<I> = PublishSubject.create()
    private val statesObservable: Observable<S> = compose()
    private val disposables = CompositeDisposable()

    abstract fun getIntentFilters(): ObservableTransformer<I, I>

    abstract fun getIdleState(): S

    abstract fun getReducer(): BiFunction<S, in R, S>?

    private fun mapIntentToAction(intent: I) = intent.mapToAction()

    private fun compose(): Observable<S> {
        return intentsSubject
            .compose(getIntentFilters())
            .map(this::mapIntentToAction)
            .compose(actionProcessor.actionProcessor)
            // Cache each state and pass it to the reducer to create a new state from
            // the previous cached one and the latest Result emitted from the action processor.
            // The Scan operator is used here for the caching.
            .scan(getIdleState(), getReducer())
            // When a reducer just emits previousState, there's no reason to call render. In fact,
            // redrawing the UI in cases like this can cause jank (e.g. messing up snackbar animations
            // by showing the same snackbar twice in rapid succession).
            .distinctUntilChanged()
            // Emit the last one event of the stream on subscription
            // Useful when a View rebinds to the ViewModel after rotation.
            .replay(1)
            // Create the stream on creation without waiting for anyone to subscribe
            // This allows the stream to stay alive even when the UI disconnects and
            // match the stream's lifecycle to the ViewModel's one.
            .autoConnect(0)
    }

    override fun processIntents(intents: Observable<I>) {
        disposables.add(intents.subscribe(intentsSubject::onNext))
    }

    override fun states(): Observable<S> = statesObservable

    override fun onCleared() {
        disposables.dispose()
    }
}

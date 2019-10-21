package com.mona.mvi.ui.base.interfaces

import io.reactivex.Observable

interface MviViewModel<I : BaseIntent, S : BaseViewState> {
  fun processIntents(intents: Observable<I>)

  fun states(): Observable<S>
}

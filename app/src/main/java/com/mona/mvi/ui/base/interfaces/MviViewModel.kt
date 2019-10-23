package com.mona.mvi.ui.base.interfaces

import io.reactivex.Observable

interface MviViewModel<A:BaseAction, I : BaseIntent<A>, S : BaseViewState> {
  fun processIntents(intents: Observable<I>)

  fun states(): Observable<S>
}

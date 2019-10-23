package com.mona.mvi.ui.base.interfaces

import io.reactivex.Observable

interface BaseView<A:BaseAction, I : BaseIntent<A>, in S : BaseViewState> {

  fun intents(): Observable<I>

  fun render(state: S)
}

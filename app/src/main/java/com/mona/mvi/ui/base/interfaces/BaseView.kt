package com.mona.mvi.ui.base.interfaces

import io.reactivex.Observable

interface BaseView<I : BaseIntent, in S : BaseViewState> {

  fun intents(): Observable<I>

  fun render(state: S)
}

package com.mona.mvi.ui.base

import io.reactivex.Observable

interface BaseView<I : BaseIntent, in S : BaseViewState> {

  fun intents(): Observable<I>

  fun render(state: S)
}

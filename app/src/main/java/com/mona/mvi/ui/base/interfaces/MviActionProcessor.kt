package com.mona.mvi.ui.base.interfaces

import io.reactivex.ObservableTransformer

interface MviActionProcessor <A:BaseAction, R: BaseResult> {
    var actionProcessor : ObservableTransformer<A, R>
}
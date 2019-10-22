package com.mona.mvi.ui.base.interfaces

interface BaseIntent<A: BaseAction>{
    fun mapToAction(): A
}
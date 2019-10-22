package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.ui.base.BaseViewModel
import com.mona.mvi.ui.base.interfaces.MviViewModel
import io.reactivex.Observable
import javax.inject.Inject

class ActorsListViewModel @Inject constructor():BaseViewModel<ActorsListRepository>(),
    MviViewModel<ActorsListIntent, ActorsListViewState>{

    override fun processIntents(intents: Observable<ActorsListIntent>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun states(): Observable<ActorsListViewState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

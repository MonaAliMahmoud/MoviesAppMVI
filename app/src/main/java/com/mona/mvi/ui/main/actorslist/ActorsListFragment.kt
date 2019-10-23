package com.mona.mvi.ui.main.actorslist

import android.os.Bundle
import android.view.View
import com.mona.mvi.R
import com.mona.mvi.ui.base.BaseFragment
import com.mona.mvi.ui.base.interfaces.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ActorsListFragment : BaseFragment(),
    BaseView<ActorsListAction, ActorsListIntent, ActorsListViewState> {

    private val disposables = CompositeDisposable()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var viewModel: ActorsListViewModel

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        bind()
    }

    fun bind() {
        // Subscribe to the ViewModel and call render for every emitted state
        disposables.add(viewModel.states().subscribe(this::render))
        // Pass the UI's intents to the ViewModel
        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<ActorsListIntent> {
        return Observable.merge(
            initialIntent(),
            initialIntent()
        )
    }

    override fun render(state: ActorsListViewState) {
        if (state.error != null) {
            return
        }
        if (state.actors.isEmpty()) {

        }
        else{}
    }

    private fun initialIntent(): Observable<ActorsListIntent.InitialIntent> {
        return Observable.just(ActorsListIntent.InitialIntent)
    }
}

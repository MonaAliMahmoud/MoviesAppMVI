package com.mona.mvi.ui.main.actorslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mona.mvi.R
import com.mona.mvi.data.model.ActorInfo
import com.mona.mvi.ui.base.BaseFragment
import com.mona.mvi.ui.base.interfaces.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

class ActorsListFragment : BaseFragment(),
    BaseView<ActorsListAction, ActorsListIntent, ActorsListViewState> {

    private val disposables = CompositeDisposable()

    private var listAdapter = ActorsAdapter()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        bind()
    }

    private fun bind() {
        disposables.add(actorsListViewModel.states().subscribe(this::render))
        actorsListViewModel.processIntents(intents())
    }

    override fun intents(): Observable<ActorsListIntent> {
        return Observable.merge(
            initialIntent(),
            initialIntent()
        )
    }

    override fun render(state: ActorsListViewState) {
        if (!state.isLoading){
            listAdapter.addItems(state.actors)
        }
        if (state.error != null) {
            return
        }
        if (state.actors.isEmpty()) {

        }
    }

    private fun initialIntent(): Observable<ActorsListIntent.InitialIntent> {
        return Observable.just(ActorsListIntent.InitialIntent)
    }

    private fun setupList() {
        actorsList.adapter = listAdapter
        actorsList.setHasFixedSize(true)
        actorsList.setItemViewCacheSize(20)
        val listLayoutManager = LinearLayoutManager(context)
        actorsList.layoutManager = listLayoutManager
    }
}

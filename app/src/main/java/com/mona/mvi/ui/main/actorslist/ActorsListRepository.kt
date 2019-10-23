package com.mona.mvi.ui.main.actorslist

import com.mona.mvi.data.api.RetrofitApi
import com.mona.mvi.data.model.ActorsList
import com.mona.mvi.ui.base.BaseRepository
import io.reactivex.Single
import javax.inject.Inject

class ActorsListRepository@Inject constructor() : BaseRepository(){

    @Inject
    lateinit var retrofitApi: RetrofitApi

    fun getActorsData(pageNum: Int =1): Single<ActorsList> {
         return retrofitApi.getPopularData(pageNum)
    }
}

package com.mona.mvi.data.api

import com.mona.mvi.data.model.ActorsList
import com.mona.mvi.di.module.NetworkModule.Companion.POPULAR_PEOPLE_URL
import com.mona.mvi.di.module.NetworkModule.Companion.SEARCH_URL
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET(POPULAR_PEOPLE_URL)
    fun getPopularData(@Query("page") page: Int): Single<ActorsList>

    @GET(SEARCH_URL)
    fun searchForActors(@Query ("query") searchStr: String,
                        @Query ("page") page: Int): Single<ActorsList>
}
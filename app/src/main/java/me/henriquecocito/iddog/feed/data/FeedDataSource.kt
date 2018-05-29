package me.henriquecocito.iddog.feed.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedDataSource {

    @GET("/feed")
    fun feed(@Query("category") category: String) : Observable<FeedResponse>
}
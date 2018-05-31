package me.henriquecocito.iddog.feed.data

import io.reactivex.Observable

interface FeedRepositoryInterface {

    fun feed(category: String, token: String) : Observable<FeedResponse>
}
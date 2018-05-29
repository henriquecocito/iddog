package me.henriquecocito.iddog.feed.domain

import io.reactivex.Observable
import me.henriquecocito.iddog.feed.data.FeedResponse

interface FeedInterface {
    fun feed(category: String, token: String) : Observable<FeedResponse>
}
package me.henriquecocito.iddog.feed.domain

import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.feed.data.FeedRepository

class FeedInteractor : BaseInteractor(), FeedInterface {

    private val repository = FeedRepository()

    override fun feed(category: String, token: String) = repository.feed(category, token).execute()

}
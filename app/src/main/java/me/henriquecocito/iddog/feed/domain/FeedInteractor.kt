package me.henriquecocito.iddog.feed.domain

import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.feed.data.FeedRepository
import me.henriquecocito.iddog.feed.data.FeedRepositoryInterface

class FeedInteractor(private val repository: FeedRepositoryInterface) : BaseInteractor(), FeedInterface {

    constructor() : this(FeedRepository())

    override fun feed(category: String, token: String) = repository.feed(category, token).execute()

}
package me.henriquecocito.iddog.feed.data

import me.henriquecocito.iddog.base.data.BaseRepository

class FeedRepository : BaseRepository(), FeedRepositoryInterface {

    override fun feed(category: String, token: String) = getAPI(FeedDataSource::class.java, token).feed(category)
}
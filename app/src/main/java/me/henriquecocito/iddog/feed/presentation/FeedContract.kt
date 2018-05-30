package me.henriquecocito.iddog.feed.presentation

import me.henriquecocito.iddog.base.presentation.BasePresenterContract
import me.henriquecocito.iddog.base.presentation.BaseViewContract

interface FeedContract {
    interface View : BaseViewContract {
        fun showItems(list: MutableList<String>)
        fun showEmptyView()
        fun hideEmptyView()
        fun showNetworkError(e: Throwable)
        fun hideNetworkError()
        fun showUnknownError(e: Throwable)
        fun hideUnknownError()
        fun openLogin()
    }

    interface Presenter : BasePresenterContract {
        fun load(category: String)
    }
}
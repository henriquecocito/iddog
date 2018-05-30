package me.henriquecocito.iddog.feed.presentation

import android.accounts.AccountsException
import android.content.Context
import me.henriquecocito.iddog.account.domain.AccountInteractor
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.feed.domain.FeedInteractor
import me.henriquecocito.iddog.feed.domain.FeedInterface
import retrofit2.HttpException
import java.net.UnknownHostException

class FeedPresenter(
        private val view: FeedContract.View,
        private val feedInteractor: FeedInterface,
        private val accountInteractor: AccountInterface) : FeedContract.Presenter {

    constructor(context: Context, view: FeedContract.View) : this(view, FeedInteractor(), AccountInteractor(context))

    override fun start() {
    }

    override fun load(category: String) {
        hideErrors()
        view.showLoading()
        accountInteractor
                .get()
                .flatMap {feedInteractor.feed(category, it.token)}
                .subscribe({showItems(it.list)}, {showError(it)}, {view.hideLoading()})
    }

    private fun showItems(list: MutableList<String>) {
        if(list.size > 0)
            view.showItems(list)
        else
            view.showEmptyView()
    }

    private fun showError(e: Throwable) {
        when(e) {
            is UnknownHostException -> view.showNetworkError(e)
            is HttpException -> view.showError(e)
            is AccountsException -> {
                view.openLogin()
                view.finish()
            }
            else -> view.showUnknownError(e)
        }
        view.hideLoading()
    }

    private fun hideErrors() {
        view.hideEmptyView()
        view.hideNetworkError()
        view.hideUnknownError()
    }
}
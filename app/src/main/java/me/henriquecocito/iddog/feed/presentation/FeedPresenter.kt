package me.henriquecocito.iddog.feed.presentation

import android.content.Context
import me.henriquecocito.iddog.account.domain.AccountInteractor
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.feed.domain.FeedInteractor
import me.henriquecocito.iddog.feed.domain.FeedInterface

class FeedPresenter(context: Context, private val view: FeedContract.View) : FeedContract.Presenter {

    private val feedInteractor : FeedInterface = FeedInteractor()
    private val accountInteractor : AccountInterface = AccountInteractor(context)

    override fun start() {
    }

    override fun load(category: String) {
        view.showLoading()
        accountInteractor
                .get()
                .flatMap {
                    feedInteractor.feed(category, it.token)
                }.subscribe({
                    view.showItems(it.list)
                }, {
                    view.showError(it)
                    view.hideLoading()
                }, {
                    view.hideLoading()
                })
    }
}
package me.henriquecocito.iddog.login.presentation

import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.base.presentation.BasePresenterContract
import me.henriquecocito.iddog.base.presentation.BaseViewContract

interface LoginContract {
    interface View : BaseViewContract {
        fun login(view: android.view.View)
        fun openFeed()
    }

    interface Presenter : BasePresenterContract {
        fun login(email: String)
    }
}
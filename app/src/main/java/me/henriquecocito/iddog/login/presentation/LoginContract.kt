package me.henriquecocito.iddog.login.presentation

import me.henriquecocito.iddog.base.BasePresenterContract
import me.henriquecocito.iddog.base.BaseViewContract

interface LoginContract {
    interface View : BaseViewContract {
        fun login(view: android.view.View)
    }

    interface Presenter : BasePresenterContract {
        fun login(email: String)
    }
}
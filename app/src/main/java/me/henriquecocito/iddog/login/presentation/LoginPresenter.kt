package me.henriquecocito.iddog.login.presentation

import android.content.Context
import me.henriquecocito.iddog.login.domain.LoginInteractor
import me.henriquecocito.iddog.login.domain.LoginInterface

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    val interactor : LoginInterface = LoginInteractor()

    override fun start() {

    }

    override fun login(email: String) {
        view.showLoading()
        interactor.doLogin(email, onNext = {
            val i = 0
        }, onError = {
            view.showError(it)
            view.hideLoading()
        }, onComplete = {
            view.hideLoading()
        })
    }
}
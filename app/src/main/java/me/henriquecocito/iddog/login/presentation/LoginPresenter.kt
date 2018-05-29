package me.henriquecocito.iddog.login.presentation

import android.content.Context
import me.henriquecocito.iddog.account.AccountInteractor
import me.henriquecocito.iddog.account.AccountInterface
import me.henriquecocito.iddog.account.AccountManager
import me.henriquecocito.iddog.login.domain.LoginInteractor
import me.henriquecocito.iddog.login.domain.LoginInterface

class LoginPresenter(private val context: Context, private val view: LoginContract.View) : LoginContract.Presenter {

    val loginInteractor : LoginInterface = LoginInteractor()
    val accountInteractor : AccountInterface = AccountInteractor(context)

    override fun start() {

    }

    override fun login(email: String) {
        view.showLoading()
        loginInteractor.doLogin(email, onNext = {
            saveAccount(it.email, it.token)
        }, onError = {
            view.showError(it)
            view.hideLoading()
        }, onComplete = {
            view.hideLoading()
        })
    }

    private fun saveAccount(email: String, token: String) {
        if(accountInteractor.save(email, token)) {
            view.openFeed()
            view.finish()
        } else {
            view.showError(Throwable(AccountManager.ERROR_UNKNOWN))
        }
    }
}
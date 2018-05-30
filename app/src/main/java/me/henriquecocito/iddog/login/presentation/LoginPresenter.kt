package me.henriquecocito.iddog.login.presentation

import android.content.Context
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.account.domain.AccountInteractor
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.login.data.model.User
import me.henriquecocito.iddog.login.domain.LoginInteractor
import me.henriquecocito.iddog.login.domain.LoginInterface

class LoginPresenter(
        private val view: LoginContract.View,
        loginInteractor: LoginInterface,
        accountInteractor: AccountInterface) : LoginContract.Presenter {

    constructor(context: Context, view: LoginContract.View) : this(view, LoginInteractor(), AccountInteractor(context))

    private val loginInteractor : LoginInterface = loginInteractor
    private val accountInteractor : AccountInterface = accountInteractor

    override fun start() {
        getAccount()
    }

    override fun login(email: String) {
        view.showLoading()
        loginInteractor
                .doLogin(email)
                .subscribe({
                    saveAccount(it)
                }, {
                    view.showError(it)
                    view.hideLoading()
                }, {
                    view.hideLoading()
                })
    }

    private fun saveAccount(user: User) {
        accountInteractor
                .save(user)
                .subscribe({view.openFeed()}, {view.showError(it)}, {view.finish()})
    }

    private fun getAccount() {
        view.showLoading()
        accountInteractor
                .get()
                .subscribe({view.openFeed()}, {view.hideLoading()}, {view.finish()})
    }
}
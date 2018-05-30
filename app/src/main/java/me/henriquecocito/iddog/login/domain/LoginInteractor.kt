package me.henriquecocito.iddog.login.domain

import io.reactivex.Observable
import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.login.data.LoginRepository
import me.henriquecocito.iddog.login.data.LoginRepositoryInterface
import me.henriquecocito.iddog.login.data.model.LoginResponse
import me.henriquecocito.iddog.login.data.model.User

class LoginInteractor(private val repository: LoginRepositoryInterface) : BaseInteractor(), LoginInterface {

    constructor() : this(LoginRepository())

    companion object {
        const val ERROR_EMPTY = "error_empty_email"
    }

    override fun doLogin(email: String) : Observable<User> {
        return validate(email).flatMap {
            repository.signIn(it)
        }.flatMap {
            Observable.just(it.user)
        }.execute()
    }

    private fun validate(email: String): Observable<String> {
        if(email.isEmpty())
            return Observable.error(Throwable(ERROR_EMPTY))

        return Observable.just(email)
    }
}
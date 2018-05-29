package me.henriquecocito.iddog.login.domain

import io.reactivex.Observable
import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.login.data.LoginRepository
import me.henriquecocito.iddog.login.data.model.LoginResponse
import me.henriquecocito.iddog.login.data.model.User

class LoginInteractor : BaseInteractor(), LoginInterface {

    private val repository = LoginRepository()

    private companion object {
        const val ERROR_EMPTY = "error_empty_email"
    }

    override fun doLogin(email: String) : Observable<User> {
        return validate(email).flatMap {
            Observable.just(it.user)
        }.execute()
    }

    private fun validate(email: String): Observable<LoginResponse> {
        if(email.isEmpty())
            return Observable.error(Throwable(ERROR_EMPTY))

        return repository.signIn(email)
    }
}
package me.henriquecocito.iddog.login.domain

import io.reactivex.Observable
import me.henriquecocito.iddog.base.BaseInteractor
import me.henriquecocito.iddog.login.data.LoginRepository
import me.henriquecocito.iddog.login.data.model.LoginResponse
import me.henriquecocito.iddog.login.data.model.User

class LoginInteractor : BaseInteractor(), LoginInterface {

    private val repository = LoginRepository()

    private companion object {
        const val ERROR_EMPTY = "error_empty_email"
        const val ERROR_UNKNOWN = "error_unknown"
    }

    override fun doLogin(email: String, onNext: (it: User) -> Unit, onError: (it: Throwable) -> Unit, onComplete: () -> Unit) {
        validate(email).execute({
            if(it.error == null)
                onNext(it.user)
            else
                onError(Throwable(it.error["message"]))
        }, {
            onError(Throwable(ERROR_UNKNOWN))
        }, {onComplete()})
    }

    private fun validate(email: String): Observable<LoginResponse> {
        if(email.isEmpty())
            return Observable.error(Throwable(ERROR_EMPTY))

        return repository.login(email)
    }
}
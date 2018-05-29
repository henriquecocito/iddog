package me.henriquecocito.iddog.account.domain

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import me.henriquecocito.iddog.account.data.AccountRepository
import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.login.data.model.User

class AccountInteractor(context: Context) : BaseInteractor(), AccountInterface {

    companion object {
        const val ERROR_NOT_FOUND = "error_not_found"
        const val ERROR_UNKNOWN = "error_unknown"
    }

    private val repository = AccountRepository(context)

    override fun save(user: User) : Observable<User> {
        return repository
                .saveAccount(user)
    }

    override fun get() : Observable<User>{
        return repository
                .getAccount()
    }
}
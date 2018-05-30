package me.henriquecocito.iddog.account.domain

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import me.henriquecocito.iddog.account.data.AccountRepository
import me.henriquecocito.iddog.account.data.AccountRepositoryInterface
import me.henriquecocito.iddog.base.domain.BaseInteractor
import me.henriquecocito.iddog.login.data.model.User

class AccountInteractor(private val repository : AccountRepositoryInterface) : BaseInteractor(), AccountInterface {

    constructor(context: Context) : this(AccountRepository(context))

    companion object {
        const val ERROR_NOT_FOUND = "error_not_found"
        const val ERROR_UNKNOWN = "error_unknown"
    }

    override fun save(user: User) : Observable<User> {
        return repository
                .saveAccount(user)
    }

    override fun get() : Observable<User>{
        return repository
                .getAccount()
    }
}
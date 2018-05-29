package me.henriquecocito.iddog.account.data

import android.accounts.Account
import android.content.Context
import io.reactivex.Observable
import me.henriquecocito.iddog.account.AccountManager
import me.henriquecocito.iddog.base.data.BaseRepository
import me.henriquecocito.iddog.login.data.model.User

class AccountRepository(private val context: Context) : BaseRepository() {

    companion object {
        private const val ERROR_NOT_FOUND = "error_not_found"
        private const val ERROR_UNKNOWN = "error_unknown"
    }

    fun saveAccount(user: User) : Observable<User> {
        return Observable.create<User> { subscriber ->
            val account = Account(user.email, AccountManager.ACCOUNT_TYPE)
            AccountManager.with(context)?.addAccountExplicitly(account, user.token, null)?.let {
                if(it) {
                    subscriber.onNext(user)
                    subscriber.onComplete()
                }
                return@create
            }
            subscriber.onError(Throwable(ERROR_UNKNOWN))
        }
    }

    fun getAccount() : Observable<User> {
        return Observable.create<User> {subscriber ->
            val accountManager = AccountManager.with(context)

            accountManager?.getAccountsByType(AccountManager.ACCOUNT_TYPE)?.firstOrNull()?.let {
                subscriber.onNext(User(it.name, accountManager.getPassword(it)!!))
                subscriber.onComplete()
                return@create
            }
            subscriber.onError(Throwable(ERROR_NOT_FOUND))
        }
    }

}
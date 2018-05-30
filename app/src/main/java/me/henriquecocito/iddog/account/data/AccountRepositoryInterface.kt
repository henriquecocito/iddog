package me.henriquecocito.iddog.account.data

import io.reactivex.Observable
import me.henriquecocito.iddog.login.data.model.User

interface AccountRepositoryInterface {

    fun saveAccount(user: User) : Observable<User>
    fun getAccount() : Observable<User>
}
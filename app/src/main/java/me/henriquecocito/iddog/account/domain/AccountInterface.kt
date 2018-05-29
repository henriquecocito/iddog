package me.henriquecocito.iddog.account.domain

import io.reactivex.Observable
import io.reactivex.Observer
import me.henriquecocito.iddog.login.data.model.User

interface AccountInterface {
    fun save(user: User) : Observable<User>
    fun get() : Observable<User>
}
package me.henriquecocito.iddog.login.domain

import io.reactivex.Observable
import me.henriquecocito.iddog.login.data.model.User

interface LoginInterface {
    fun doLogin(email: String) : Observable<User>
}
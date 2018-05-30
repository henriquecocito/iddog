package me.henriquecocito.iddog.login.data

import io.reactivex.Observable
import me.henriquecocito.iddog.login.data.model.LoginResponse

interface LoginRepositoryInterface {

    fun signIn(email: String) : Observable<LoginResponse>
}
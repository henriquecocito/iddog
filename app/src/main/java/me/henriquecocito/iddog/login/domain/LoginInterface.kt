package me.henriquecocito.iddog.login.domain

import me.henriquecocito.iddog.login.data.model.User

interface LoginInterface {
    fun doLogin(email: String, onNext: (it: User) -> Unit, onError: (it: Throwable) -> Unit, onComplete: () -> Unit)
}
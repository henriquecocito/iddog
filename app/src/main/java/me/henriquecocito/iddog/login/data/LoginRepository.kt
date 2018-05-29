package me.henriquecocito.iddog.login.data

import me.henriquecocito.iddog.base.data.BaseRepository

class LoginRepository : BaseRepository() {

    fun signIn(email: String) = getAPI(LoginDataSource::class.java, null).login(email)
}
package me.henriquecocito.iddog.login.data

import me.henriquecocito.iddog.base.BaseRepository

class LoginRepository : BaseRepository() {

    fun login(email: String) = getAPI(LoginDataSource::class.java).login(email)
}
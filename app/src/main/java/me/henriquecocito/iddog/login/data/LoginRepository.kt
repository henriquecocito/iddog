package me.henriquecocito.iddog.login.data

import me.henriquecocito.iddog.base.data.BaseRepository

open class LoginRepository : BaseRepository(), LoginRepositoryInterface {

    override fun signIn(email: String) = getAPI(LoginDataSource::class.java, null).login(email)
}
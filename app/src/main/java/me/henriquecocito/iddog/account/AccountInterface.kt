package me.henriquecocito.iddog.account

interface AccountInterface {
    fun save(email: String, token: String): Boolean
}
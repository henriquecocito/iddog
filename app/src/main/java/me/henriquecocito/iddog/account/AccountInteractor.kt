package me.henriquecocito.iddog.account

import android.accounts.Account
import android.content.Context
import me.henriquecocito.iddog.base.BaseInteractor

class AccountInteractor(val context: Context) : BaseInteractor(), AccountInterface {

    override fun save(email: String, token: String): Boolean {
        val account = Account(email, AccountManager.ACCOUNT_TYPE)
        return AccountManager.with(context)?.addAccountExplicitly(account, token, null) ?: false
    }
}
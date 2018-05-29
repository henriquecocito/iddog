package me.henriquecocito.iddog.account

import android.content.Context

abstract class AccountManager private constructor() {

    companion object {
        const val ACCOUNT_TYPE = "me.henriquecocito.iddog"

        private var accountManager : android.accounts.AccountManager? = null

        fun with(context: Context) : android.accounts.AccountManager? {
            if(accountManager == null) {
                accountManager =  android.accounts.AccountManager.get(context)
            }

            return accountManager
        }
    }
}
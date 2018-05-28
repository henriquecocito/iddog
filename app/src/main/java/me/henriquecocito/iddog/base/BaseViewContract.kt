package me.henriquecocito.iddog.base

interface BaseViewContract {
    fun finish()
    fun showLoading()
    fun hideLoading()
    fun showError(e: Throwable)
}
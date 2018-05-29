package me.henriquecocito.iddog.base.presentation

interface BaseViewContract {
    fun finish()
    fun showLoading()
    fun hideLoading()
    fun showError(e: Throwable)
}
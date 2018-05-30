package me.henriquecocito.iddog.base.domain

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor {

    fun <T> Observable<T>.execute() : Observable<T> {
        return observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}
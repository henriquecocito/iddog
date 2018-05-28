package me.henriquecocito.iddog.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor() {

    protected fun <T> Observable<T>.execute(onNext: (it: T) -> Unit, onError: (it : Throwable) -> Unit, onComplete: () -> Unit) {
        // TODO: Implements error handling
        observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({onNext(it)}, {onError(it)}, {onComplete()})
    }
}
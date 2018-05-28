package me.henriquecocito.iddog.login.data

import io.reactivex.Observable
import me.henriquecocito.iddog.login.data.model.LoginResponse
import retrofit2.http.*

interface LoginDataSource {

    @POST("signup")
    @FormUrlEncoded
    fun login(@Field("email") email : String) : Observable<LoginResponse>
}
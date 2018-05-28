package me.henriquecocito.iddog.login.data.model

import com.squareup.moshi.Json

data class User(@Json(name = "email") val email: String,
                @Json(name = "token") val token: String)
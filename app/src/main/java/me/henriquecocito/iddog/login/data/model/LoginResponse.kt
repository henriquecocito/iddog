package me.henriquecocito.iddog.login.data.model

import com.squareup.moshi.Json

data class LoginResponse( @Json(name = "user") val user: User,
                          @Json(name = "error") val error: MutableMap<String, String>
)
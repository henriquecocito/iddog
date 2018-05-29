package me.henriquecocito.iddog.feed.data

import com.squareup.moshi.Json

data class FeedResponse(@Json(name = "category") val category: String,
                        @Json(name = "list") val list: MutableList<String>)
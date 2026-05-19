package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Search (

  @Json(name = "q"   ) var q   : String?       = null,
  @Json(name = "rid" ) var rid : String?       = null,
  @Json(name = "rc"  ) var rc  : String?       = null,
  @Json(name = "sr"  ) var sr  : List<Sr> = listOf()

)
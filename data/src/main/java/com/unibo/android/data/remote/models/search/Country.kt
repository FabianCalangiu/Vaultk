package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country (

  @Json(name = "name"     ) var name     : String? = null,
  @Json(name = "isoCode2" ) var isoCode2 : String? = null,
  @Json(name = "isoCode3" ) var isoCode3 : String? = null

)
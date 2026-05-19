package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coordinates (

  @Json(name = "lat"  ) var lat  : String? = null,
  @Json(name = "long" ) var long : String? = null

)
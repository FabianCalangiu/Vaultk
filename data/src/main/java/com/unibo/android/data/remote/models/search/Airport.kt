package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Airport (

  @Json(name = "airportCode" ) var airportCode : String? = null,
  @Json(name = "airportId"   ) var airportId   : String? = null,
  @Json(name = "metrocode"   ) var metrocode   : String? = null,
  @Json(name = "multicity"   ) var multicity   : String? = null

)
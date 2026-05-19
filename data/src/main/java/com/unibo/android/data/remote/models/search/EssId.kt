package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EssId (

  @Json(name = "sourceName" ) var sourceName : String? = null,
  @Json(name = "sourceId"   ) var sourceId   : String? = null

)
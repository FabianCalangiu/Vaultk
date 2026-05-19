package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegionNames (

  @Json(name = "fullName"             ) var fullName             : String? = null,
  @Json(name = "shortName"            ) var shortName            : String? = null,
  @Json(name = "displayName"          ) var displayName          : String? = null,
  @Json(name = "primaryDisplayName"   ) var primaryDisplayName   : String? = null,
  @Json(name = "secondaryDisplayName" ) var secondaryDisplayName : String? = null,
  @Json(name = "lastSearchName"       ) var lastSearchName       : String? = null

)
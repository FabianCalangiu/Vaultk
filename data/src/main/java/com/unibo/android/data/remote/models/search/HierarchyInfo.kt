package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HierarchyInfo (

  @Json(name = "country" ) var country : Country? = Country(),
  @Json(name = "airport" ) var airport : Airport? = Airport()

)
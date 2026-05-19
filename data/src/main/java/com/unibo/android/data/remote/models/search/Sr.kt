package com.unibo.android.data.remote.models.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sr (
  @Json(name = "@type"         ) var atType         : String?        = null,
  @Json(name = "index"         ) var index         : String?        = null,
  @Json(name = "gaiaId"        ) var gaiaId        : String?        = null,
  @Json(name = "type"          ) var type          : String?        = null,
  @Json(name = "regionNames"   ) var regionNames   : RegionNames?   = RegionNames(),
  @Json(name = "essId"         ) var essId         : EssId?         = EssId(),
  @Json(name = "coordinates"   ) var coordinates   : Coordinates?   = Coordinates(),
  @Json(name = "hierarchyInfo" ) var hierarchyInfo : HierarchyInfo? = HierarchyInfo()
)
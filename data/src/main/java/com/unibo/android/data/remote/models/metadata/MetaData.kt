package com.unibo.android.data.remote.models.metadata

import com.squareup.moshi.Json

data class MetaData (
  @Json(name = "GB"    ) var GB    : GB?    = GB(),
  @Json(name = "IT"    ) var IT    : IT?    = IT(),
  @Json(name = "US"    ) var US    : US?    = US(),
)
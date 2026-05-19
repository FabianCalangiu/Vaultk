package com.unibo.android.data.remote.models.metadata

import com.squareup.moshi.Json

data class AppLanguage (
  @Json(name = "ios" ) var ios : String? = null
)
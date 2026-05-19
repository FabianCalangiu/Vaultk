package com.unibo.android.data.remote.models.metadata

import com.squareup.moshi.Json

data class SupportedLocales (

  @Json(name = "appLanguage"                  ) var appLanguage                  : AppLanguage?    = AppLanguage(),
  @Json(name = "localeIdentifier"             ) var localeIdentifier             : String?         = null,
  @Json(name = "languageCode"                 ) var languageCode                 : String?         = null,
  @Json(name = "languageIdentifier"           ) var languageIdentifier           : Int?            = null,
  @Json(name = "localeBasedPointOfSaleName"   ) var localeBasedPointOfSaleName   : String?         = null,
  @Json(name = "appInfoURL"                   ) var appInfoURL                   : String?         = null,
  @Json(name = "createAccountMarketingText"   ) var createAccountMarketingText   : String?         = null,
  @Json(name = "forgotPasswordURL"            ) var forgotPasswordURL            : String?         = null,
  @Json(name = "appSupportURLs"               ) var appSupportURLs               : AppSupportURLs? = AppSupportURLs(),
  @Json(name = "bookingSupportURL"            ) var bookingSupportURL            : String?         = null,
  @Json(name = "websiteURL"                   ) var websiteURL                   : String?         = null,
  @Json(name = "accountURL"                   ) var accountURL                   : String?         = null,
  @Json(name = "resultsSortFAQLegalLink"      ) var resultsSortFAQLegalLink      : String?         = null,
  @Json(name = "termsAndConditionsURL"        ) var termsAndConditionsURL        : String?         = null,
  @Json(name = "termsOfBookingURL"            ) var termsOfBookingURL            : String?         = null,
  @Json(name = "learnAboutSortAndFilterURL"   ) var learnAboutSortAndFilterURL   : String?         = null,
  @Json(name = "privacyPolicyURL"             ) var privacyPolicyURL             : String?         = null,
  @Json(name = "loyaltyTermsAndConditionsURL" ) var loyaltyTermsAndConditionsURL : String?         = null,
  @Json(name = "coronavirusInfoUrl"           ) var coronavirusInfoUrl           : String?         = null

)
package com.unibo.android.data.remote.models.metadata

import com.squareup.moshi.Json

data class US (

  @Json(name = "siteId"                                ) var siteId                                : Int?                        = null,
  @Json(name = "TPID"                                  ) var TPID                                  : Int?                        = null,
  @Json(name = "EAPID"                                 ) var EAPID                                 : Int?                        = null,
  @Json(name = "flightsEnabled"                        ) var flightsEnabled                        : Boolean?                    = null,
  @Json(name = "carsEnabled"                           ) var carsEnabled                           : Boolean?                    = null,
  @Json(name = "lxEnabled"                             ) var lxEnabled                             : Boolean?                    = null,
  @Json(name = "packagesEnabled"                       ) var packagesEnabled                       : Boolean?                    = null,
  @Json(name = "swpEnabled:hotels"                     ) var swpEnabledHotels                     : Boolean?                    = null,
  @Json(name = "earnMessageEnabled:hotels"             ) var earnMessageEnabledHotels             : Boolean?                    = null,
  @Json(name = "doNotSellMyPersonalInformationEnabled" ) var doNotSellMyPersonalInformationEnabled : Boolean?                    = null,
  @Json(name = "carsWebViewEnabled"                    ) var carsWebViewEnabled                    : Boolean?                    = null,
  @Json(name = "url"                                   ) var url                                   : String?                     = null,
  @Json(name = "countryCode"                           ) var countryCode                           : String?                     = null,
  @Json(name = "supportedLocales"                      ) var supportedLocales                      : List<SupportedLocales> = emptyList(),
  @Json(name = "branding"                              ) var branding                              : String?                     = null,
  @Json(name = "deepLinkDateFormat"                    ) var deepLinkDateFormat                    : String?                     = null,
  @Json(name = "pointOfSaleId"                         ) var pointOfSaleId                         : Int?                        = null,
  @Json(name = "automaticallyMappedLocales"            ) var automaticallyMappedLocales            : List<String>           = emptyList(),
  @Json(name = "shouldDisplayAveragePrices"            ) var shouldDisplayAveragePrices            : Boolean?                    = null,
  @Json(name = "supportsVipAccess"                     ) var supportsVipAccess                     : Boolean?                    = null,
  @Json(name = "shouldShowRewards"                     ) var shouldShowRewards                     : Boolean?                    = null,
  @Json(name = "marketingOptIn"                        ) var marketingOptIn                        : String?                     = null,
  @Json(name = "showPackageFreeUnrealDeal"             ) var showPackageFreeUnrealDeal             : Boolean?                    = null,
  @Json(name = "hotMIPSavingsPercentage"               ) var hotMIPSavingsPercentage               : String?                     = null,
  @Json(name = "cookiePolicyURLString"                 ) var cookiePolicyURLString                 : String?                     = null,
  @Json(name = "enrollInLoyalty"                       ) var enrollInLoyalty                       : Boolean?                    = null

)
package com.unibo.android.data.repositories

import android.content.Context
import android.util.Log
import com.unibo.android.data.local.db.AppDatabase
import com.unibo.android.data.local.entities.AccomodationTypeLocalModel
import com.unibo.android.data.remote.api.RetrofitClient
import com.unibo.android.domain.models.AccommodationType
import com.unibo.android.domain.repositories.AccommodationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AccommodationRepositoryImpl(
    private val context: Context,
    private val retrofitClient: RetrofitClient
): AccommodationRepository {

    companion object {
        private const val TAG = "AccommodationRepository"
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    //TODO ROOM 6. Ottenere un'istanza di AppDatabase e usare il DAO:
    private val accomadationTypeDao = AppDatabase.getInstance(context = context).accomodationTypeDao()

    private fun getMockUiAccomodationTypes() = listOf(
        AccommodationType.Hotel(
            hotelName = "Hotel Rosalba",
            hotelDescription = "Questa è la descrizione dell'hotel Rosalba che si trova a Cesena, in via Marina 4",
            hotelPictureUrl = "https://picsum.photos/200/300",
            hotelScore = 8.4
        ),
        AccommodationType.Hotel(
            hotelName = "Hotel Internazionale",
            hotelDescription = "Questa è la descrizione dell'hotel Internazionale che si trova a Cesena, in via Marina 4",
            hotelPictureUrl = "https://picsum.photos/200/300",
            hotelScore = 7.5
        ),

        AccommodationType.Apartment(
            apartmentName = "Appartamenti Sul Mare",
            apartmentDescription = "Questa è la descrizione degli appartamenti sul Mare che si trova a Cesena, in via Marina 4",
            apartmentPictureUrl = "https://picsum.photos/200/300",
            apartmentScore = 9.8
        ),

        AccommodationType.Hotel(
            hotelName = "Hotel Cesare",
            hotelDescription = "Questa è la descrizione dell'hotel Cesare che si trova a Cesena, in via Marina 4",
            hotelPictureUrl = "https://picsum.photos/200/300",
            hotelScore = 3.8
        ),

        AccommodationType.Apartment(
            apartmentName = "Appartamenti Nuovi",
            apartmentDescription = "Questa è la descrizione degli appartamenti Nuovi che si trova a Cesena, in via Marina 4",
            apartmentPictureUrl = "https://picsum.photos/200/300",
            apartmentScore = 7.2
        ),

        AccommodationType.Hotel(
            hotelName = "Hotel Majestic",
            hotelDescription = "Questa è la descrizione dell'hotel Majestic che si trova a Cesena, in via Marina 4",
            hotelPictureUrl = "https://picsum.photos/200/300",
            hotelScore = 8.0
        ),

        AccommodationType.Apartment(
            apartmentName = "Appartamenti Cesira",
            apartmentDescription = "Questa è la descrizione degli appartamenti Cesira che si trova a Cesena, in via Marina 4",
            apartmentPictureUrl = "https://picsum.photos/200/300",
            apartmentScore = 6.7
        ),
    )

    private val _accommodationTypeList = MutableStateFlow<List<AccommodationType>>(emptyList())
    override val accommodationTypeList: StateFlow<List<AccommodationType>> = _accommodationTypeList

    override fun startFetchAccommodationList() {
//        scope.launch {
//            delay(5.seconds)
//            _accommodationTypeList.emit(
//                getMockUiAccomodationTypes()
//            )
//        }

        fetchAccomodationTypeListFromDB()
        //fetchAccommodationTypeListFromAPI()
    }

    fun fetchAccommodationTypeListFromAPI() {
        scope.launch {
            val hotelsMetadata = retrofitClient.hotelsService.getMetadata()
            println("Hotels metadata: $hotelsMetadata")

            val search = retrofitClient.hotelsService.search(
                q = "Bologna",
                locale = "it_IT",
                langid = 1033,
                siteId = 300000001
            )
            println("Hotels search: $search")

            val hotelList = search.sr.filter {
                it.type == "HOTEL"
            }.map {
                AccommodationType.Hotel(
                    hotelName = it.regionNames?.displayName ?: "",
                    hotelDescription = "",
                    hotelPictureUrl = "",
                    hotelScore = 9.8
                )
            }

            println("Hotels search?: $hotelList")
            _accommodationTypeList.emit(hotelList)
        }
    }

    fun fetchAccomodationTypeListFromDB() {
        //TODO ROOM 7. Attraverso il DAO ottiene lista di AccomodationTypes
        scope.launch {
            val dbList = accomadationTypeDao.getAllAccomodationTypes()
            Log.d(TAG, "Database list: ${dbList.size}")
            dbList.onEach {
                Log.d(TAG, "DB ENTITY $it")
            }
            val accomadationTypeList = dbList.mapNotNull { it.toAccomodationType() }

            accomadationTypeList.onEach {
                Log.d(TAG, "ITEM $it")
            }
            _accommodationTypeList.emit(accomadationTypeList)
        }
    }



    private fun AccomodationTypeLocalModel.toAccomodationType(): AccommodationType? {
        return when (this.type) {
            "Hotel" -> {
                AccommodationType.Hotel(
                    hotelName = this.name,
                    hotelDescription = this.description,
                    hotelPictureUrl = this.pictureUrl ?: "",
                    hotelScore = this.score,
                )
            }

            "Apartment" -> {
                AccommodationType.Apartment(
                    apartmentName = this.name,
                    apartmentDescription = this.description,
                    apartmentPictureUrl = this.pictureUrl ?: "",
                    apartmentScore = this.score,
                )
            }

            else -> null
        }

    }
}
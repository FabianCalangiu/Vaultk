package com.unibo.android.data.local.entities

import androidx.room.*


//TODO ROOM 3. Definizione dell'entità
@Entity(tableName = "accomodation_types")
data class AccomodationTypeLocalModel(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val pictureUrl: String?,
    val score: Double,
    val type : String,
    val isFavourite : Boolean
)

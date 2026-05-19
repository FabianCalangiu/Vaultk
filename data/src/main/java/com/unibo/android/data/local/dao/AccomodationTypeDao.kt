package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.unibo.android.data.local.entities.AccomodationTypeLocalModel


//TODO ROOM 4. Definizione del DAO
@Dao
interface AccomodationTypeDao {
    @Query("SELECT * FROM accomodation_types")
    fun getAllAccomodationTypes(): List<AccomodationTypeLocalModel>
}

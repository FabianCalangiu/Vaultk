package com.unibo.android.domain.repositories

import kotlinx.coroutines.flow.Flow

//TODO DATASTORE 2A definire Repository
interface PreferenceRepository {
    val needShowOnBoarding: Flow<Boolean>
    suspend fun setOnboardingShowed()
}
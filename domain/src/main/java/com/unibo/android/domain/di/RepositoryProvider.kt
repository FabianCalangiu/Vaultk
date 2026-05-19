package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.AccommodationRepository
import com.unibo.android.domain.repositories.PreferenceRepository

interface RepositoryProvider {
    val accommodationRepository: AccommodationRepository
    //TODO DATASTORE 2C definire PreferenceRepository nel RepositoryProvider
    val preferenceRepository: PreferenceRepository
}
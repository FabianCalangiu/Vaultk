package com.unibo.android.data.di

import android.content.Context
import com.unibo.android.data.remote.api.RetrofitClient
import com.unibo.android.data.repositories.AccommodationRepositoryImpl
import com.unibo.android.data.repositories.PreferencesManagerImpl
import com.unibo.android.domain.di.RepositoryProvider
import com.unibo.android.domain.repositories.AccommodationRepository
import com.unibo.android.domain.repositories.PreferenceRepository

class RepositoryProviderImpl(
    private val context: Context
): RepositoryProvider {

    private val retrofitClient = RetrofitClient()

    override val accommodationRepository: AccommodationRepository = AccommodationRepositoryImpl(
        context = context,
        retrofitClient = retrofitClient
    )
    //TODO DATASTORE 2C definire PreferenceRepository nel RepositoryProvider
    override val preferenceRepository: PreferenceRepository = PreferencesManagerImpl(context = context)
}
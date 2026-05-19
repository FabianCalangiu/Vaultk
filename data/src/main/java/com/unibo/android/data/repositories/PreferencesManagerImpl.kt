package com.unibo.android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.unibo.android.domain.repositories.PreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//TODO DATASTORE 2B Usare un repository per salvare i dati su DataStore

// Estensione per creare il DataStore
private val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class PreferencesManagerImpl(private val context: Context) : PreferenceRepository {

    companion object {
        private val SHOW_ONBOARDING_KEY = booleanPreferencesKey("show_onboarding")
    }

    // Recupera il valore booleano (se non esiste, default = true)
    override val needShowOnBoarding: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SHOW_ONBOARDING_KEY] ?: true
        }

    // Salva il valore booleano
    override suspend fun setOnboardingShowed() {
        context.dataStore.edit { preferences ->
            preferences[SHOW_ONBOARDING_KEY] = false
        }
    }
}

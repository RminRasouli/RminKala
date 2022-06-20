package com.pourkazemi.mahdi.mymaktabplus.data.localdetabase.data_store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.project.rminkala.data.local.data_store.PreferencesInfo
import ir.project.rminkala.data.local.data_store.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore_settings")

@Singleton
class SettingDataStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStore

    val preferences: Flow<PreferencesInfo> = dataStore.data.catch { cause ->
        Log.e("datastore_error", cause.message.toString())
    }.map { preference ->
        val theme: Theme = Theme.valueOf(preference[SettingPreferencesKey.KEY_THEME] ?: Theme.AUTO.name)
        PreferencesInfo(
            theme = theme,
        )
    }

    suspend fun updateTheme(theme: Theme) {
        dataStore.edit {
            it[SettingPreferencesKey.KEY_THEME] = theme.name
        }
    }

    private object SettingPreferencesKey {
        val KEY_THEME = stringPreferencesKey("preferences_them")
    }
}

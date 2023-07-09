package io.upnextgpt.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "settings")

class SettingsImpl(private val dataStore: DataStore<Preferences>) : Settings {
    private val currentPlayerKey = stringPreferencesKey("current_player")

    private val nextTrackIdKey = longPreferencesKey("next_track")

    private val apiBaseUrlKey = stringPreferencesKey("api_base_url")

    override val currentPlayerFlow: Flow<String?> = dataStore.data
        .map { it[currentPlayerKey] }
        .distinctUntilChanged()

    override suspend fun updateCurrentPlayer(value: String?) {
        dataStore.edit {
            if (value == null) {
                it.remove(currentPlayerKey)
            } else {
                it[currentPlayerKey] = value
            }
        }
    }

    override val nextTrackIdFlow: Flow<Long?> = dataStore.data
        .map { it[nextTrackIdKey] }
        .distinctUntilChanged()

    override suspend fun updateNextTrackId(value: Long?) {
        dataStore.edit {
            if (value == null) {
                it.remove(nextTrackIdKey)
            } else {
                it[nextTrackIdKey] = value
            }
        }
    }

    override val apiBaseUrlFlow: Flow<String?> = dataStore.data
        .map { it[apiBaseUrlKey] }
        .distinctUntilChanged()

    override suspend fun updateApiBaseUrl(value: String?) {
        dataStore.edit {
            if (value == null) {
                it.remove(apiBaseUrlKey)
            } else {
                it[apiBaseUrlKey] = value
            }
        }
    }
}
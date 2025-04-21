package com.learning.talentaisproject.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "user_prefs"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class UsernameDataStore(private val context: Context) {

    companion object {
        val KEY_USERNAME = stringPreferencesKey("username")
    }

    val usernameFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_USERNAME]
        }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
        }
    }

    suspend fun clearUsername() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_USERNAME)
        }
    }
}

// aca vamos a manejar la logica para recuperar los tokens y mantener la sesion usando data store que creo que tengo que desacargar

package com.example.donpedro.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_session") // Instancia de dataSotore para guardar la sesion
class SessionManager(private val context: Context) {
    companion object {
        // TODO : encriptar los tokens antes de guardarlos
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token") // Clave de token de acceso
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token") // Clave de token de refresco que es la mas larga

    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    fun getAccessToken(): Flow<String?> = context.dataStore.data.map { it[ACCESS_TOKEN_KEY] }

    fun getRefreshToken(): Flow<String?> = context.dataStore.data.map { it[REFRESH_TOKEN_KEY] }

    suspend fun clearTokens() {
        context.dataStore.edit { it.clear() }
    }
}
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
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    suspend fun saveUserData(id: String, name: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = id
            preferences[USER_NAME_KEY] = name
            preferences[USER_EMAIL_KEY] = email
        }
    }


    fun getUserId(): Flow<String?> = context.dataStore.data.map { it[USER_ID_KEY] }

    fun getUserName(): Flow<String?> = context.dataStore.data.map { it[USER_NAME_KEY] }

    fun getUserEmail(): Flow<String?> = context.dataStore.data.map { it[USER_EMAIL_KEY] }

    fun getAccessToken(): Flow<String?> = context.dataStore.data.map { it[ACCESS_TOKEN_KEY] }

    fun getRefreshToken(): Flow<String?> = context.dataStore.data.map { it[REFRESH_TOKEN_KEY] }

    suspend fun clearTokens() {
        context.dataStore.edit { it.clear() }
    }

}
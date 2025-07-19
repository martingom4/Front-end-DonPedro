// Aca vamos a crear el repositorio que se encargara de llamar al servicio de la API
package com.example.donpedro.repository

import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.data.ClientApiService
import com.example.donpedro.data.RegisterRequest
import com.example.donpedro.data.RegisterResponse

class ClientRepository(
    private val api: ClientApiService,
    private val sessionManager: SessionManager
) {
    suspend fun registerClient(request: RegisterRequest): RegisterResponse {
        val response = api.registerClient(request)
        sessionManager.saveTokens(
            accessToken = response.tokens.accessToken,
            refreshToken = response.tokens.refreshToken
        )

        return response
    }
}
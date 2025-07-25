// Aca vamos a crear el repositorio que se encargara de llamar al servicio de la API
package com.example.donpedro.repository

import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.data.ClientApiService
import com.example.donpedro.data.LogoutRequest
import com.example.donpedro.data.RegisterRequest
import com.example.donpedro.data.RegisterResponse
import com.example.donpedro.data.LoginRequest
import com.example.donpedro.data.LoginResponse
import com.example.donpedro.data.UpdateUserDto
import com.example.donpedro.data.UpdateUserResponse
import com.example.donpedro.data.local.Product
import com.example.donpedro.data.local.PromotionDto
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Response


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

    suspend fun login(request: LoginRequest): LoginResponse {
        val request = LoginRequest(request.email, request.password)
        val response = api.login(request)

        sessionManager.saveTokens(
            accessToken = response.tokens.accessToken,
            refreshToken = response.tokens.refreshToken
        )

        sessionManager.saveUserData(
            id = response.user.id,
            name = response.user.name,
            email = response.user.email
        )

        return response
    }
    suspend fun logout(): Response<Unit> {
        val refreshToken = sessionManager.getRefreshToken().firstOrNull()
        return if (!refreshToken.isNullOrBlank()) {
            api.logout(LogoutRequest(refreshToken))
        } else {
            Response.error(400, okhttp3.ResponseBody.create(null, "No refresh token"))
        }
    }

    suspend fun updateUser(
        userId: String,
        name: String? = null,
        email: String? = null
    ): UpdateUserResponse {
        val dto = UpdateUserDto(
            userId = userId,
            name = name,
            email = email
        )

        val updated = api.updateUser(dto)

        // Actualiza la caché local
        sessionManager.saveUserData(
            id = updated.id,
            name = updated.name,
            email = updated.email
        )

        return updated
    }

    suspend fun getProducts(): List<Product>{
        return api.getProducts()
    }

    suspend fun getPromotions(): List<PromotionDto> {
        return api.getPromotions()
    }
}
// aca vamos a crear el servicio de la API para el registro y login de los clientes
package com.example.donpedro.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val user: User,
    val tokens: Token
)

data class User(
    val id: String,
    val name: String,
    val email: String
)
data class Token(
    val accessToken: String,
    val refreshToken: String
)



interface ClientApiService {
    @POST("auth/register")
    suspend fun registerClient(@Body request: RegisterRequest): RegisterResponse
}
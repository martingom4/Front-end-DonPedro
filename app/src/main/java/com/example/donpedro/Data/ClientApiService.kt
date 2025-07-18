// aca vamos a crear el servicio de la API para el registro y login de los clientes
package com.example.donpedro.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
data class RegisterResponse( // TODO el backend no responde con este objeto, hay que cambiarlo
    val success: Boolean,
    val message: String
)

interface ClientApiService {
    @POST("auth/register")
    suspend fun registerClient(@Body request: RegisterRequest): Response<RegisterResponse>
}
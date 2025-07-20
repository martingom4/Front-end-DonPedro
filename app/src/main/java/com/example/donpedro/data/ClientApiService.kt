// aca vamos a crear el servicio de la API para el registro y login de los clientes
package com.example.donpedro.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Part
import retrofit2.http.PATCH

import com.example.donpedro.data.UpdateUserDto
import com.example.donpedro.data.UpdateUserResponse
import com.example.donpedro.data.local.Product
import com.example.donpedro.data.local.PromotionDto
import retrofit2.http.GET

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val user: User,
    val tokens: Token
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
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

data class LogoutRequest(
    val refreshToken: String
)

interface ClientApiService {
    @POST("auth/register")
    suspend fun registerClient(@Body request: RegisterRequest): RegisterResponse

    @POST("auth/logout")
    suspend fun logout(@Body request: LogoutRequest): Response<Unit>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @PATCH("api/users/update")
    suspend fun updateUser(@Body request: UpdateUserDto): UpdateUserResponse

    @GET("api/products")
    suspend fun getProducts(): List<Product>

    @GET("api/promotions")
    suspend fun getPromotions(): List<PromotionDto>
    
}
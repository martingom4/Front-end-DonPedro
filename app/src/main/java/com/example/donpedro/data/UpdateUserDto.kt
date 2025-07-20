package com.example.donpedro.data

data class UpdateUserDto(
    val userId: String,
    val name: String? = null,
    val email: String? = null
)
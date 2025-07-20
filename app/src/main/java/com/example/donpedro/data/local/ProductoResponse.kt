package com.example.donpedro.data.local

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val stock : Number
)
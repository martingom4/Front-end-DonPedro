package com.example.donpedro.data.local

data class PromotionDto(
    val id: String,
    val title: String,
    val discountPct: Int,
    val products: List<Product>
)
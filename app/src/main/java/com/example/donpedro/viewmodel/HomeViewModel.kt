package com.example.donpedro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donpedro.data.local.Product

import com.example.donpedro.data.local.PromotionDto
import com.example.donpedro.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repo: ClientRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _promotions = MutableStateFlow<List<PromotionDto>>(emptyList())
    val products: StateFlow<List<Product>> = _products
    val promotions: StateFlow<List<PromotionDto>> = _promotions

    init {
        viewModelScope.launch {
            _products.value = repo.getProducts()
            _promotions.value = repo.getPromotions()   // obtiene promociones
        }
    }
}
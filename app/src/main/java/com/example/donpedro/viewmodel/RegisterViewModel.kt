// se encarga de manejar la logica del negocio y el screen se registro llama a este viewmodel
// y este viewmodel se encarga de llamar al repositorio y al servicio de la API

// RegisterViewModel.kt
package com.example.donpedro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donpedro.data.ClientRepository
import com.example.donpedro.data.remote.RegisterRequest
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class RegisterViewModel(private val repository: ClientRepository) : ViewModel() {
    var registerResult by mutableStateOf<String?>(null)
        private set

    fun register(email: String, password: String, name: String) {
        viewModelScope.launch {
            val response = repository.registerClient(RegisterRequest(email, password, name))
            registerResult = if (response.isSuccessful) {
                response.body()?.message
            } else {
                "Registration failed"
            }
        }
    }
}
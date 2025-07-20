// se encarga de manejar la logica del negocio y el screen se registro llama a este viewmodel
// y este viewmodel se encarga de llamar al repositorio y al servicio de la API

// RegisterViewModel.kt
package com.example.donpedro.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.donpedro.data.RegisterRequest
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.repository.ClientRepository


class RegisterViewModel(
    private val repository: ClientRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var registerResult by mutableStateOf<String?>(null)
        private set

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.registerClient(
                    RegisterRequest(name = name, email = email, password = password)
                )
                registerResult = "Registro completo Bienvenido ${response.user.name}"
                sessionManager.saveTokens(response.tokens.accessToken, response.tokens.refreshToken)
                sessionManager.saveUserData(
                    id = response.user.id,
                    name = response.user.name,
                    email = response.user.email
                )

            } catch (e: Exception) {
                registerResult = "Error: ${e.message}"
            }
        }
    }
}
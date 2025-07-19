package com.example.donpedro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donpedro.data.LoginRequest
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: ClientRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(email = email, password = password))

                // Guardar tokens
                sessionManager.saveTokens(response.tokens.accessToken, response.tokens.refreshToken)


                // Guardar datos del usuario
                sessionManager.saveUserData(
                    id = response.user.id,
                    name = response.user.name,
                    email = response.user.email
                )

                _loginResult.value = "Login exitoso. Bienvenido ${response.user.name}"

            } catch (e: Exception) {
                _loginResult.value = "Error: ${e.message}"
            }
        }
    }
}
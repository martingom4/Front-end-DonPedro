package com.example.donpedro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.repository.ClientRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionManager: SessionManager,
    private val repository: ClientRepository
): ViewModel(){
    fun logout(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.logout()
                if (response.isSuccessful) {
                    sessionManager.clearTokens()
                    onSuccess()
                } else {
                    onError("Error al cerrar sesión")
                }
            } catch (e: Exception) {
                onError("Error: ${e.localizedMessage}")
            }
        }
    }
}
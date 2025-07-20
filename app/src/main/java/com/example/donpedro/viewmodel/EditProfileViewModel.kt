package com.example.donpedro.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.repository.ClientRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: ClientRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    // Campos editables
    var name by mutableStateOf("")
    var email by mutableStateOf("")

    // Mensajes de UI (éxito / error)
    var uiMessage = mutableStateOf<String?>(null)
        private set

    init {
        // Precargar datos del usuario desde DataStore
        viewModelScope.launch {
            name  = sessionManager.getUserName().first() ?: ""
            email = sessionManager.getUserEmail().first() ?: ""
        }
    }

    /**
     * Guarda los cambios y ejecuta [onSuccess] si todo va bien.
     * En caso de error, actualiza [uiMessage].
     */
    fun saveChanges(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val id = sessionManager.getUserId().first()
                    ?: throw IllegalStateException("Usuario no encontrado")

                // Sólo enviamos los campos que hayan cambiado
                val oldName  = sessionManager.getUserName().first()
                val oldEmail = sessionManager.getUserEmail().first()

                val newName  = if (name != oldName) name else null
                val newEmail = if (email != oldEmail) email else null

                repository.updateUser(id, newName, newEmail)

                uiMessage.value = "Cambios guardados"
                onSuccess()
            } catch (e: Exception) {
                uiMessage.value = "Error: ${e.localizedMessage ?: "desconocido"}"
            }
        }
    }
}
// esto es para el ViewModelFactory que se usa para crear instancias de RegisterViewModel
package com.example.donpedro.viewmodel

// RegisterViewModelFactory.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.donpedro.data.ClientRepository
import com.example.donpedro.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private val repository: ClientRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
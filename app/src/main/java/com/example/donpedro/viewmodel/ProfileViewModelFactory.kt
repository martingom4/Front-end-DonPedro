package com.example.donpedro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.repository.ClientRepository


class ProfileViewModelFactory(
    private val sessionManager: SessionManager,
    private val repository: ClientRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(sessionManager, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.donpedro.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.donpedro.data.local.SessionManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SessionViewModel(private val sessionManager: SessionManager) : ViewModel() {
    var isLoggedIn by mutableStateOf<Boolean?>(null)
        private set
    init{
        checkSession()
    }
    private fun checkSession(){
        viewModelScope.launch{
            val token = sessionManager.getAccessToken().first()
            isLoggedIn = !token.isNullOrEmpty()
        }
    }

}
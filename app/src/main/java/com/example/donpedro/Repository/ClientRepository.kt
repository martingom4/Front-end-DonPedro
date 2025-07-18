// Aca vamos a crear el repositorio que se encargara de llamar al servicio de la API
package com.example.donpedro.data

import com.example.donpedro.data.remote.ClientApiService
import com.example.donpedro.data.remote.RegisterRequest

class ClientRepository(private val api: ClientApiService) {
    suspend fun registerClient(request: RegisterRequest) = api.registerClient(request)
}
// esta es la instancia de retrofit para la conexiion a el api
package com.example.donpedro.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.donpedro.data.ClientApiService

object RetrofitInstance {
    val api: ClientApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientApiService::class.java)
    }
}
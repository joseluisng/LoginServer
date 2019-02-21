package com.joseluisng.loginserver

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("usuario" )
    fun registrarUsuario(@Body usuario: Usuario?): Call<Usuario>

}
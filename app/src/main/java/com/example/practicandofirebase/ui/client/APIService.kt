package com.example.practicandofirebase.ui.client

import com.example.practicandofirebase.ui.model.MascotaModel
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("mascotaperdida.php")
    fun listarMascota(): Call<List<MascotaModel>>
}
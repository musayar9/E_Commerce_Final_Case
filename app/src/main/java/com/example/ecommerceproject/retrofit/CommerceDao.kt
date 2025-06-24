package com.example.ecommerceproject.retrofit

import com.example.ecommerceproject.data.entity.CommerceResponse
import retrofit2.http.GET


/// http://kasimadalan.pe.hu/urunler/tumUrunleriGetir.php

interface CommerceDao {
    // GetAllAllProduct
    @GET("urunler/tumUrunleriGetir.php")
    suspend fun getAllProducts():CommerceResponse

}
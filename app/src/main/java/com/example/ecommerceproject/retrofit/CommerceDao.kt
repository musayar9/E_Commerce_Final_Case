package com.example.ecommerceproject.retrofit

import com.example.ecommerceproject.data.entity.CRUDResponse
import com.example.ecommerceproject.data.entity.CommerceResponse
import com.example.ecommerceproject.data.entity.ProductBasketResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST


/// http://kasimadalan.pe.hu/urunler/tumUrunleriGetir.php

interface CommerceDao {
    // GetAllAllProduct
    @GET("urunler/tumUrunleriGetir.php")
    suspend fun getAllProducts():CommerceResponse


    @POST("urunler/sepeteUrunEkle.php")
    suspend fun addToProductBasket(
        @Field("ad")ad:String,
        @Field("resim")resim:String,
        @Field("kategori")kategori:String,
        @Field("fiyat")fiyat:Int,
        @Field("marka")marka:String,
        @Field("siparisAdeti")siparisAdeti:Int,
        @Field("kullaniciAdi")kullaniciAdi:String
    ):CRUDResponse


    @POST("urunler/sepettekiUrunleriGetir.php")
    suspend fun getToProductsBasket(
        @Field("kullaniciAdi")kullaniciAdi:String):ProductBasketResponse

}
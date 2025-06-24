package com.example.ecommerceproject.data.repo

import com.example.ecommerceproject.data.datasource.CommerceDataSource
import com.example.ecommerceproject.data.entity.Products

class CommerceRepository(var commerceDataSource: CommerceDataSource) {

    suspend fun getAllProducts():List<Products> =commerceDataSource.getAllProducts()

    suspend fun addToProductBasket(ad:String, resim:String, kategori:String, fiyat:Int, marka:String,
                                   siparisAdeti:Int, kullaniciAdi:String) =
        commerceDataSource.addToProductBasket(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
}
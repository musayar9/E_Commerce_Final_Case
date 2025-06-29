package com.example.ecommerceproject.data.entity

import java.io.Serializable

data class ProductBaskets(
    var sepetId:Int,
    var ad:String,
    var resim:String,
    var kategori:String,
    var marka:String,
    var fiyat:Int,
    var siparisAdeti:Int,
    var kullaniciAdi:String
):Serializable
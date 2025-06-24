package com.example.ecommerceproject.data.entity

import java.io.Serializable

data class Products(
    var id:Int,
    var ad:String,
    var resim:String,
    var kategori:String,
    var fiyat:Int,
    var marka:String

) :Serializable
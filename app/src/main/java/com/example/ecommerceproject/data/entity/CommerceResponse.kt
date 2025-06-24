package com.example.ecommerceproject.data.entity

import java.io.Serializable

data class CommerceResponse(var urunler:List<Products>,
                       var success:Int):Serializable
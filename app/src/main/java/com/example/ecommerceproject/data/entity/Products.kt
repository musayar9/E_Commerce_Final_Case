package com.example.ecommerceproject.data.entity

import android.os.Parcelable
import java.io.Serializable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Products(
    var id:Int,
    var ad:String,
    var resim:String,
    var kategori:String,
    var fiyat:Int,
    var marka:String
) :Parcelable
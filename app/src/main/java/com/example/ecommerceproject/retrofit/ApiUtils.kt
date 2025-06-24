package com.example.ecommerceproject.retrofit

class ApiUtils {
    companion object{
        val baseUrl = "http://kasimadalan.pe.hu/"

        fun getProductsDao():CommerceDao{
            return  RetrofitClient.getClient(baseUrl).create(CommerceDao::class.java)

        }
    }
}
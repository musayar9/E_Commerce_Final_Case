package com.example.ecommerceproject.data.repo

import com.example.ecommerceproject.data.datasource.CommerceDataSource
import com.example.ecommerceproject.data.entity.Products

class CommerceRepository(var commerceDataSource: CommerceDataSource) {

    suspend fun getAllProducts():List<Products> =commerceDataSource.getAllProducts()
}
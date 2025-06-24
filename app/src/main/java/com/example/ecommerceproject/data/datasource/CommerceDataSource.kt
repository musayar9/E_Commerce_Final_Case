package com.example.ecommerceproject.data.datasource

import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.retrofit.CommerceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommerceDataSource(var commerceDao: CommerceDao) {

    // burada tüm ürünleri çekiyoruz
suspend fun getAllProducts():List<Products> = withContext(Dispatchers.IO){
    return@withContext commerceDao.getAllProducts().urunler
}
}
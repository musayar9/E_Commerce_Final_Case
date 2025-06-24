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

suspend fun addToProductBasket(ad:String, resim:String, kategori:String, fiyat:Int, marka:String,
                               siparisAdeti:Int, kullaniciAdi:String){
    commerceDao.addToProductBasket(ad, resim, kategori,
        fiyat, marka, siparisAdeti, kullaniciAdi)
}

}
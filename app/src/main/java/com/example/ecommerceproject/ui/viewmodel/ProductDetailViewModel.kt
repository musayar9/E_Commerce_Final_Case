package com.example.ecommerceproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecommerceproject.data.repo.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
    fun addToProductBasket(ad:String, resim:String, kategori:String, fiyat:Int, marka:String,
                           siparisAdeti:Int, kullaniciAdi:String){
        Log.e("productViewModel","cliked prodviewmdeol")
        CoroutineScope(Dispatchers.Main).launch {
            commerceRepository.addToProductBasket(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
        }
    }
}
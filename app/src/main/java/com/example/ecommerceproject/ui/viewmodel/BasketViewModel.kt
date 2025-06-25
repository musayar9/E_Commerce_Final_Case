package com.example.ecommerceproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceproject.data.entity.ProductBaskets
import com.example.ecommerceproject.data.repo.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
    var basketProductsList = MutableLiveData<List<ProductBaskets>>()

    init{
        getToProductsBasket("musa_sayar")
    }

    fun getToProductsBasket(kullaniciAdi:String){
        CoroutineScope(Dispatchers.Main).launch {
            basketProductsList.value = commerceRepository.getToProductsBasket(kullaniciAdi)
        }
    }

    fun deleteProductBasket(sepetId:Int, kullaniciAdi: String){
        CoroutineScope(Dispatchers.Main).launch {
            commerceRepository.deleteProductBasket(sepetId, kullaniciAdi)
            getToProductsBasket(kullaniciAdi)
        }
    }
}
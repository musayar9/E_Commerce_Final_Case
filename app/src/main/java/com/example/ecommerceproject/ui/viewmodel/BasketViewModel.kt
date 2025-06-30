package com.example.ecommerceproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBindings
import com.example.ecommerceproject.data.entity.ProductBaskets
import com.example.ecommerceproject.data.repo.CommerceRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout.LengthCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.ecommerceproject.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
    var basketProductsList = MutableLiveData<List<ProductBaskets>>()

    init{
        getToProductsBasket("musa_sayar")
    }

    fun getToProductsBasket(kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = commerceRepository.getToProductsBasket(kullaniciAdi)
                basketProductsList.value = result
            } catch (e: Exception) {

                Log.e("error", "${e.message}")
                e.printStackTrace()
                basketProductsList.value = emptyList()
            }
        }
    }

    fun deleteProductBasket(sepetId:Int, kullaniciAdi: String){
        CoroutineScope(Dispatchers.Main).launch {
            commerceRepository.deleteProductBasket(sepetId, kullaniciAdi)
            getToProductsBasket(kullaniciAdi)
        }
    }


    fun formatPrice(price: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
        Log.d("MainViewModel", "Price is ${format.format(price)}")
        return format.format(price)
    }

}
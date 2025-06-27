package com.example.ecommerceproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.data.repo.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
    var productsList = MutableLiveData<List<Products>>()
    private val favoriteListArray = MutableLiveData<ArrayList<Products>>(java.util.ArrayList())
    val favoriteList: LiveData<ArrayList<Products>> get() = favoriteListArray
    init{
        getAllProducts()
    }

    fun getAllProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value = commerceRepository.getAllProducts()
        }
    }

    fun addFavorite(product: Products) {
        val currentList = favoriteListArray.value?.toMutableList() ?: ArrayList()
        if (!currentList.any { it.id == product.id }) {
            currentList.add(product)
            favoriteListArray.value = ArrayList(currentList)
            Log.d("MainViewModel", "Added to favorites: $product,")
        } else {
            Log.d("MainViewModel", "Product already in favorites: $product")
        }
    }

    fun removeFavorite(product: Products) {
        val currentList = favoriteListArray.value?.toMutableList() ?: ArrayList()
        currentList.removeIf { it.id == product.id }
        favoriteListArray.value = ArrayList(currentList)
        Log.d("MainViewModel", "Removed from favorites: $product")
    }

    fun formatPrice(price: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
        Log.d("MainViewModel", "Price is ${format.format(price)}")
        return format.format(price)
    }
}
package com.example.ecommerceproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.data.repo.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
    var productsList = MutableLiveData<List<Products>>()

    init{
        getAllProducts()
    }

    fun getAllProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value = commerceRepository.getAllProducts()
        }
    }
}
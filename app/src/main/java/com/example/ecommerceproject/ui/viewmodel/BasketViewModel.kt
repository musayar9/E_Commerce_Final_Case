package com.example.ecommerceproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecommerceproject.data.repo.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var commerceRepository: CommerceRepository):ViewModel() {
}
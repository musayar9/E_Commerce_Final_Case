package com.example.ecommerceproject.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.example.ecommerceproject.databinding.ProductDetailScreenBinding

import com.example.ecommerceproject.R.id.toBasketScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailScreen : Fragment() {

    private lateinit var binding : ProductDetailScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProductDetailScreenBinding.inflate(inflater, container, false)

        binding.addBasketBtn.setOnClickListener{
            it.findNavController().navigate(toBasketScreen)
        }
     return binding.root
    }


}
package com.example.ecommerceproject.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.ecommerceproject.databinding.ProductDetailScreenBinding

import com.example.ecommerceproject.R.id.toBasketScreen
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import com.example.ecommerceproject.ui.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailScreen : Fragment() {

    private lateinit var binding : ProductDetailScreenBinding
    private lateinit var viewModel:ProductDetailViewModel
    private lateinit var mainViewModel: MainViewModel
    private   var amount:Int = 1
    private var productPrice:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProductDetailScreenBinding.inflate(inflater, container, false)


        val bundle : ProductDetailScreenArgs by navArgs()
        val product = bundle.productDetail

        Log.e("ProdyuctDeta", "$product")
        val imageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(this).load(imageUrl).override(128,128).into(binding.imageView)
        binding.productAmount.text = amount.toString()
        binding.productName.text = product.ad.toString()
        val price = viewModel.formatPrice(product.fiyat)
        binding.productPrice.text = price.toString()
        binding.productOnlyPrice.text = price.toString()
        binding.productBrand.text = product.marka
        binding.incrementBtn.setOnClickListener{
            amount += 1
            productPrice = amount * product.fiyat
            var convertPrice = viewModel.formatPrice(productPrice)
            binding.productAmount.setText(amount.toString())
            binding.productPrice.setText(convertPrice.toString())

        }

        binding.decrementBtn.setOnClickListener{
            if (amount <= 0  ){
             Toast.makeText(requireContext(),"Ürünün miktarı sıfırdan küçük olamaz", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
            }
            amount -= 1
            productPrice -= product.fiyat
            var convertPrice = viewModel.formatPrice(productPrice)
            binding.productAmount.setText(amount.toString())
            binding.productPrice.setText(convertPrice.toString())

        }
        binding.backButton.setOnClickListener{
findNavController().popBackStack()
        }
        binding.addBasketBtn.setOnClickListener{
            val ad = product.ad
            val fiyat  = product.fiyat
            val marka = product.marka
            val kategori = product.kategori
            val resim = product.resim
            val siparisAdeti = amount
            val kullaniciAdi = "musa_sayar"
            Log.e("Musa", "${ad}, ${kullaniciAdi}, ${fiyat}")
          viewModel.addToProductBasket(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
            Toast.makeText(requireContext(),"${ad} Sepete Eklendi", Toast.LENGTH_SHORT ).show()
        }
     return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}
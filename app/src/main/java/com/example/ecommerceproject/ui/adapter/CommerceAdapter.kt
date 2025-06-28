package com.example.ecommerceproject.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceproject.R
import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.databinding.CardDesignBinding
import com.example.ecommerceproject.ui.screens.MainScreenDirections
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import java.text.NumberFormat
import java.util.Locale
import java.util.UUID



class CommerceAdapter(var mContext:Context, var productList:List<Products>, var viewModel: MainViewModel,):
RecyclerView.Adapter<CommerceAdapter.CardHolder>()
{


    inner class CardHolder(var binding: CardDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
       val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val product = productList.get(position)

        val productDesign = holder.binding

        val imageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(mContext).load(imageUrl).override(128,128).into(productDesign.productImage)
        productDesign.textProductName.text = product.ad
//        productDesign.productCategory.text= product.kategori
//        productDesign.productBrand.text= product.marka
        val price = viewModel.formatPrice(product.fiyat)
        productDesign.productPrice.text = "${price.toString()}"

        productDesign.cardViewToDo.setOnClickListener{
            val toProductDetail = MainScreenDirections.toProductDetailScreen(productDetail = product)
            it.findNavController().navigate(toProductDetail)
        }




        val isFavorite = viewModel.favoriteList.value?.any { it.id == product.id } ?: false
        productDesign.favorite.setImageResource(
            if (isFavorite) R.drawable.favorite_regular else R.drawable.empty_favorite
        )


        productDesign.favorite.setOnClickListener {
            if (isFavorite) {
                viewModel.removeFavorite(product)
                productDesign.favorite.setImageResource(R.drawable.empty_favorite)
                Toast.makeText(mContext, "${product.ad} ürünü favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addFavorite(product)
                productDesign.favorite.setImageResource(R.drawable.favorite_regular)
                Toast.makeText(mContext, "${product.ad} ürünü favorilere eklendi", Toast.LENGTH_SHORT).show()
            }
            notifyItemChanged(position)
        }
        }

    }

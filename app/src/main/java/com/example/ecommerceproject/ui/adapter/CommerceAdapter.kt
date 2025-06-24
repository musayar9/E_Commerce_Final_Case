package com.example.ecommerceproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.databinding.CardDesignBinding
import com.example.ecommerceproject.ui.viewmodel.MainViewModel

class CommerceAdapter(var mContext:Context, var productList:List<Products>, var viewModel: MainViewModel):
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
        productDesign.productCategory.text= product.kategori
        productDesign.productBrand.text= product.marka


    }
}
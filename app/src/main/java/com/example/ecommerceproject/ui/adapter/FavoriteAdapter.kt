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
import com.example.ecommerceproject.ui.screens.FavoriteScreenDirections
import com.example.ecommerceproject.ui.screens.MainScreenDirections
import com.example.ecommerceproject.ui.viewmodel.MainViewModel

class FavoriteAdapter(var mContext:Context, var favoriteList: List<Products>, var viewModel:MainViewModel):
RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>(){

    inner class FavoriteHolder(var binding: CardDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
       val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FavoriteHolder(binding)
    }

    override fun getItemCount():Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        val product = favoriteList.get(position)
        Log.e("Favorite", "$product")
        val productDesign = holder.binding

        val imageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
        Glide.with(mContext).load(imageUrl).override(128,128).into(productDesign.productImage)
        productDesign.textProductName.text = product.ad
//        productDesign.productCategory.text= product.kategori
//        productDesign.productBrand.text= product.marka
        val price = viewModel.formatPrice(product.fiyat)
        productDesign.productPrice.text = "${price.toString()}"

        val isFavorite = viewModel.favoriteList.value?.any { it.id == product.id } ?: false
        productDesign.favorite.setImageResource(
            if (isFavorite) R.drawable.favorite_regular else R.drawable.empty_favorite
        )

        if(isFavorite){
            productDesign.favorite.setImageResource(R.drawable.favorite_regular)
        }else{
            productDesign.favorite.setImageResource(R.drawable.empty_favorite)
        }

             productDesign.favorite.setOnClickListener {
            viewModel.removeFavorite(product)
             productDesign.favorite.setImageResource(R.drawable.empty_favorite)
            Toast.makeText(mContext, "${product.ad} ürünü favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()
            notifyItemChanged(position)
        }
        productDesign.cardViewToDo.setOnClickListener{
            val toProductDetail = FavoriteScreenDirections.toProductDetailScreen(productDetail = product)
            it.findNavController().navigate(toProductDetail)
        }
    }

    fun updateList(newList: List<Products>) {
        favoriteList = newList
        Log.d("FavoriteAdapter", "Updated list: $newList")
        notifyDataSetChanged()
    }

}
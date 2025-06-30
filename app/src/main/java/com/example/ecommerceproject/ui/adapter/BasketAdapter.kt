package com.example.ecommerceproject.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceproject.data.entity.ProductBaskets
import com.example.ecommerceproject.databinding.BasketCardBinding
import com.example.ecommerceproject.ui.viewmodel.BasketViewModel
import com.google.android.material.snackbar.Snackbar
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
class BasketAdapter(var mContext:Context, var basketList:List<ProductBaskets>, var viewModel: BasketViewModel )
    :RecyclerView.Adapter<BasketAdapter.BasketHolder>()

{
    inner class BasketHolder(var binding:BasketCardBinding):RecyclerView.ViewHolder(binding.root)

    private var totalPrice:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
            val binding = BasketCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
            return BasketHolder(binding)
    }

    override fun getItemCount(): Int = basketList.size
    fun updateList(newList: List<ProductBaskets>) {
        basketList = newList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val basket = basketList.get(position)
        val basketDesign = holder.binding

        val imageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${basket.resim}"
        Glide.with(mContext).load(imageUrl).override(64,64).into(basketDesign.productImage)
        basketDesign.productName.text= "${basket.ad.toString()} / ${basket.marka} "
        totalPrice = basket.siparisAdeti * basket.fiyat
        var converPrice = viewModel.formatPrice(totalPrice)
        basketDesign.price.text = converPrice.toString()
        basketDesign.productAmount.text = "Adet: ${basket.siparisAdeti.toString()}"
        basketDesign.productCategory.text = "${basket.kategori}"


        basketDesign.deleteProduct.setOnClickListener{

            AlertDialog.Builder(mContext)
                .setTitle("${basket.ad} ")
                .setMessage("Bu ürünü sepetten çıkarmak isteğinize eminmisiniz.")
                .setPositiveButton("Evet") { dialog, _ ->
                    Log.e("Delete sepet", "deleted ${basket.sepetId}")
                    viewModel.deleteProductBasket(basket.sepetId, basket.kullaniciAdi)
                    dialog.dismiss()
                }
                .setNegativeButton("Hayır"){dialog,_ ->
                    Log.e("Delete sepet", "vazgeçi")
                    dialog.dismiss()
                }
                .show()

        }
    }
}
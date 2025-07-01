package com.example.ecommerceproject.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceproject.data.entity.Category
import com.example.ecommerceproject.databinding.CategoryFieldBinding
import com.example.ecommerceproject.R
import com.example.ecommerceproject.ui.viewmodel.MainViewModel

class CategoryAdapter(private val items:List<Category>, var viewModel: MainViewModel):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()
{

    inner class CategoryViewHolder(val binding: CategoryFieldBinding):RecyclerView.ViewHolder(binding.root)
    private lateinit var context : Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val binding = CategoryFieldBinding.inflate(LayoutInflater.from(context))
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.categoryText.text = item.categoryText.toString()
        val resourceId = holder.itemView.context.resources.getIdentifier(
            item.image,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.binding.categoryImage.setImageResource(resourceId)

        holder.binding.categoryFields.setOnClickListener{

            lastSelectedPosition= selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            Handler(Looper.getMainLooper()).postDelayed( {},  500)

        }
        if(selectedPosition == position){
            holder.binding.categoryFields.setBackgroundResource(R.drawable.selected_category_field)
            holder.binding.categoryText.setTextColor(context.resources.getColor(R.color.gray50))
            if(item.categoryText == "Hepsi"){
                viewModel.getAllProducts()
            }else{
                val filteredList = viewModel.productsList.value?.filter { it->it.kategori == item.categoryText }
                viewModel.filteredProducts.value = filteredList
            }
        }else{
            holder.binding.categoryFields.setBackgroundResource(R.drawable.category_fields)
            holder.binding.categoryText.setTextColor(context.resources.getColor(R.color.gray150))
        }

    }

}
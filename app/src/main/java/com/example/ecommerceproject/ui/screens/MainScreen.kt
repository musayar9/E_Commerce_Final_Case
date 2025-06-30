package com.example.ecommerceproject.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ecommerceproject.R

import com.example.ecommerceproject.databinding.MainScreenBinding
import com.example.ecommerceproject.R.id.toProductDetailScreen
import com.example.ecommerceproject.R.id.toBasketScreen
import com.example.ecommerceproject.R.id.toFavoriteScreen
import com.example.ecommerceproject.data.entity.Category
import com.example.ecommerceproject.data.entity.Products
import com.example.ecommerceproject.ui.adapter.CategoryAdapter
import com.example.ecommerceproject.ui.adapter.CommerceAdapter
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import java.util.Objects


@AndroidEntryPoint
class MainScreen : Fragment() {

    private lateinit var binding: MainScreenBinding
    private lateinit var  viewModel: MainViewModel
    private lateinit var commerceAdapter: CommerceAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    val categoryList = listOf(
        Category(0, "hepsi", "Hepsi"),
        Category(1, "elektronik", "Teknoloji"),
        Category(3, "aksesuar", "Aksesuar"),
        Category(4, "kozmetik", "Kozmetik")
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = MainScreenBinding.inflate(inflater, container, false)

        binding.maimProgressBar.visibility = View.VISIBLE

        val adapter = CategoryAdapter( categoryList,  viewModel)
        binding.recyclerCategory.adapter = adapter
        binding.recyclerCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        commerceAdapter = CommerceAdapter(requireContext(), mutableListOf(), viewModel)



        binding.recyclerProductList.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerProductList.adapter = commerceAdapter
//        viewModel.productsList.observe(viewLifecycleOwner){
//            val commerceAdapter = CommerceAdapter(requireContext(), it, viewModel)
//            binding.recyclerProductList.adapter = commerceAdapter
//        }
        viewModel.filteredProducts.observe(viewLifecycleOwner) { filteredList ->
            binding.maimProgressBar.visibility =View.GONE
            commerceAdapter.updateData(filteredList ?: emptyList())
        }


        binding.searchText.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String): Boolean {
               search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return true
            }
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        val tempViewModel:MainViewModel by activityViewModels()
        viewModel=tempViewModel
    }

    fun search(searchText:String){

        val products = viewModel.productsList.value
        if (products == null) {
            Log.e("search", "Product list is null")

             Toast.makeText(requireContext(), "\n" +
                     "Hiçbir ürün mevcut değil", Toast.LENGTH_SHORT).show()
            return
        }

        val filteredList = viewModel.productsList.value?.filter {
            it.ad.contains(searchText, ignoreCase = true)
        } ?: emptyList()

        viewModel.filteredProducts.value = filteredList
    }

    override fun onResume() {
        super.onResume()
        Log.e("refresh", "refreshed")
     //  viewModel.productsList.value = emptyList()
     //   viewModel.getAllProducts()
    }


}
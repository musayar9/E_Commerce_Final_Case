package com.example.ecommerceproject.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ecommerceproject.R

import com.example.ecommerceproject.databinding.MainScreenBinding
import com.example.ecommerceproject.R.id.toProductDetailScreen
import com.example.ecommerceproject.R.id.toBasketScreen
import com.example.ecommerceproject.R.id.toFavoriteScreen
import com.example.ecommerceproject.ui.adapter.CommerceAdapter
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainScreen : Fragment() {

    private lateinit var binding: MainScreenBinding
    private lateinit var  viewModel: MainViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = MainScreenBinding.inflate(inflater, container, false)
        binding.recyclerProductList.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        viewModel.productsList.observe(viewLifecycleOwner){
            val commerceAdapter = CommerceAdapter(requireContext(), it, viewModel)
            binding.recyclerProductList.adapter = commerceAdapter
        }




        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        val tempViewModel:MainViewModel by activityViewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        Log.e("refresh", "refreshed")
     //  viewModel.productsList.value = emptyList()

     //   viewModel.getAllProducts()
    }


}
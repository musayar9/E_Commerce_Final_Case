package com.example.ecommerceproject.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.ecommerceproject.databinding.MainScreenBinding
import com.example.ecommerceproject.R.id.toProductDetailScreen
import com.example.ecommerceproject.ui.adapter.CommerceAdapter
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {

    private lateinit var binding: MainScreenBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = MainScreenBinding.inflate(inflater, container, false)


        viewModel.productsList.observe(viewLifecycleOwner){
            val commerceAdapter = CommerceAdapter(requireContext(), it, viewModel)
            binding.recyclerProductList.adapter=commerceAdapter
        }
        binding.recyclerProductList.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )



        binding.producDetailBtn.setOnClickListener{
            it.findNavController().navigate(toProductDetailScreen)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        val tempViewModel:MainViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }


}
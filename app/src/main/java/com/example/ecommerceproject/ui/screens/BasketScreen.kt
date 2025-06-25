package com.example.ecommerceproject.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.ecommerceproject.R

import com.example.ecommerceproject.databinding.BasketScreenBinding
import com.example.ecommerceproject.ui.adapter.BasketAdapter
import com.example.ecommerceproject.ui.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketScreen : Fragment() {
    private lateinit var binding: BasketScreenBinding
    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BasketScreenBinding.inflate(inflater, container, false)
        viewModel.basketProductsList.observe(viewLifecycleOwner){
            val basketAdapter = BasketAdapter(requireContext(), it, viewModel)
            binding.recyclerBasketView.adapter = basketAdapter
        }
        binding.recyclerBasketView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BasketViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onResume() {
        super.onResume()
        viewModel.getToProductsBasket(kullaniciAdi = "musa_sayar")
    }


}
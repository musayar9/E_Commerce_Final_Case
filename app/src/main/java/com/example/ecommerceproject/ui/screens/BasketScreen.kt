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
    private lateinit var basketAdapter: BasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BasketScreenBinding.inflate(inflater, container, false)
        binding.recyclerBasketView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.basketProductsList.observe(viewLifecycleOwner){basketList ->
            if(basketList.isEmpty()){
                binding.emptyBasket.visibility = View.VISIBLE
                binding.recyclerBasketView.visibility = View.GONE
                basketAdapter = BasketAdapter(requireContext(), basketList, viewModel)
                binding.recyclerBasketView.adapter = basketAdapter
            }else{
                basketAdapter = BasketAdapter(requireContext(), basketList, viewModel)
                binding.recyclerBasketView.adapter = basketAdapter
                binding.emptyBasket.visibility = View.GONE
                binding.recyclerBasketView.visibility = View.VISIBLE
            }

            basketAdapter.updateList(basketList)
        }

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
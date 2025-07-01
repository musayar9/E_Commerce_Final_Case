package com.example.ecommerceproject.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ecommerceproject.databinding.FavoriteScreenBinding
import com.example.ecommerceproject.ui.adapter.FavoriteAdapter
import com.example.ecommerceproject.ui.viewmodel.FavoritViewModel
import com.example.ecommerceproject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteScreen : Fragment() {

    private lateinit var binding: FavoriteScreenBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: FavoritViewModel
    private lateinit var adapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FavoriteScreenBinding.inflate(inflater, container, false)
        binding.recyclerFavorite.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        adapter = FavoriteAdapter(requireContext(), emptyList(),mainViewModel)
        binding.recyclerFavorite.adapter = adapter

        mainViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            Log.d("favor", "Favorite List: $favoriteList")
            adapter.updateList(favoriteList)
            if (favoriteList.isEmpty()) {
                binding.recyclerFavorite.visibility = View.GONE
                binding.emptyList.visibility = View.VISIBLE
                binding.favoriteHeadField.visibility = View.GONE
                Log.d("FavoriteScreen", "Favorite list is empty")
            } else {
                binding.recyclerFavorite.visibility = View.VISIBLE
                binding.emptyList.visibility = View.GONE
                binding.favoriteHeadField.visibility = View.VISIBLE
                binding.favoriteSize.text = favoriteList.size.toString()
                Log.d("FavoriteScreen", "Favorite list is not empty: ${favoriteList.size} items")
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MainViewModel by activityViewModels()
        mainViewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        Log.e("refresh", "refreshed")


    }
}
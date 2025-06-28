package com.example.ecommerceproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import dagger.hilt.android.AndroidEntryPoint
import com.example.ecommerceproject.R.id.toBasketScreen
import com.example.ecommerceproject.R.id.toFavoriteScreen
import com.example.ecommerceproject.R.id.toMainScreen
import com.google.android.material.bottomnavigation.BottomNavigationView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationField)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)




    }
}
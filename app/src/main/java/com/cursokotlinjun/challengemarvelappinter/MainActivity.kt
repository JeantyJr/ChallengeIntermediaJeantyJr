package com.cursokotlinjun.challengemarvelappinter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cursokotlinjun.challengemarvelappinter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_fragment)
    appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_characters, R.id.nav_events))
        setupActionBarWithNavController(navController, appBarConfiguration)
        val bottomNavView = binding.bottomNavigationV
        setupWithNavController(bottomNavView, navController)
      binding.bottomNavigationV.itemIconTintList = null
       // binding.bottomNavigationV.setupWithNavController(navController)


    }

    fun setToolBarTitlle(title: String = "Marvel"){
        binding.textView1.text = title

    }


    override fun onSupportNavigateUp(): Boolean{
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun buttonNavigationHide(){
        binding.bottomNavigationV.isVisible = false
    }

    fun buttonNavigationVisible(){
        binding.bottomNavigationV.isVisible = true
    }



}
package com.gustavo.foton.desafio.marvel.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.gustavo.foton.desafio.marvel.R
import com.gustavo.foton.desafio.marvel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarController: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment

        navController = navHostFragment.navController
        appBarController = AppBarConfiguration(
            setOf()
            /*setOf(
                //R.id.charactersFragment,
               // R.id.favoritesFragment,
               // R.id.aboutFragment,
               // R.id.sortFragment
            )*/
        )

        setupActionBarWithNavController(navController, appBarController)
        //binding.bottomNaviMain.setupWithNavController(navController, appBarController)
    }
}
package com.example.dtm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dtm.R
import com.example.dtm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var lastBackPressed: Long = 0

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {

                R.id.nav_history -> {
                    navController.navigate(R.id.historyFragment)
                }

                R.id.nav_settings -> {
//                    binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.settingsFragment)
                }

                R.id.nav_favourite -> {
//                    binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.favouriteFragment)
                }

                R.id.nav_home -> {
//                    binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.homeFragment)
                }

            }
            true
        }

    }
    override fun onBackPressed() {


        if (navController.currentDestination?.id == R.id.homeFragment) {
            if (lastBackPressed + 2000 >= System.currentTimeMillis()) {
                finishAffinity()
            } else {
                lastBackPressed = System.currentTimeMillis()
                Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT)
                        .show()
            }
        } else {
            super.onBackPressed()
        }

    }

}
package com.example.dtm.ui.mainUi

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dtm.R
import com.example.dtm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import com.example.dtm.databinding.ActivityMainBinding.inflate as inflate1


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var smsCountTxt: TextView? = null
    private var lastBackPressed: Long = 0
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = inflate1(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar as androidx.appcompat.widget.Toolbar?)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        (toolbar as androidx.appcompat.widget.Toolbar).setNavigationOnClickListener {


        }
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


//        if (navController.currentDestination?.id == R.id.homeFragment) {
//            if (lastBackPressed + 2000 >= System.currentTimeMillis()) {
//                finishAffinity()
//            } else {
//                lastBackPressed = System.currentTimeMillis()
//                Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT)
//                        .show()
//            }
//        } else {}
            super.onBackPressed()


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu.findItem(R.id.action_notifications)
        val actionView: View = MenuItemCompat.getActionView(menuItem)
        smsCountTxt = actionView.findViewById<View>(R.id.notification_badge) as TextView

        return true
    }


}
package com.example.dtm.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dtm.R
import com.example.dtm.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)


        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHost.navController
    }
    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }
}
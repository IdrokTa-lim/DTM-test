package com.example.dtm.ui.registration.reg

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.dtm.ui.registration.listener.Listener

class RegistrationViewModelFactory(

    private val navController: NavController,
    private val listener: Listener,
    private val activity: Activity

):ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(navController,listener,activity) as T
        } else{
            throw IllegalArgumentException("RegistrationViewModel not found")
        }
    }

}
package com.example.dtm.registration.reg

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.dtm.registration.listener.Listener

class RegistrationViewModelFactory(
    private val navController: NavController,
    private val listener: Listener,
    private val context: Context
):ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(navController,listener,context) as T
        } else{
            throw IllegalArgumentException("RegistrationViewModel not found")
        }
    }

}
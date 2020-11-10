package com.example.dtm.registration.reg

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.dtm.registration.listener.Listener
import com.example.dtm.repository.LoginRepository
import com.example.dtm.ui.MainActivity


class RegistrationViewModel(navController: NavController, listener: Listener,private val context: Context) : ViewModel() {
    private val TAG = "RegistrationViewModel"

    var email:String? = null
    var password:String? = null
    var username:String?=null
    private var registrationRepo: LoginRepository? = null

    init {
        registrationRepo = LoginRepository.getInstance(listener)
    }


    fun onCreateButtonClicked(view: View){
        Log.d(TAG, "onCreateButtonClicked: create called")
        registrationRepo?.createAccount(email, password)
    }

    fun onSigninButtonClicked(view: View){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        //Log.d(TAG, "onSigninButtonClicked: sign in called")
        registrationRepo?.signIn(email, password)
    }
}
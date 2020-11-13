package com.example.dtm.ui.registration.reg

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.dtm.ui.registration.listener.Listener
import com.example.dtm.repository.LoginRepository
import com.google.android.material.button.MaterialButton


class RegistrationViewModel(navController: NavController, listener: Listener, activity:Activity) : ViewModel() {
    private val TAG = "RegistrationViewModel"

    var email:String? = null
    var password:String? = null
    var username:String?=null
    var isSignIn = true
    private var registrationRepo: LoginRepository? = null

    init {
        registrationRepo = LoginRepository.getInstance(listener,activity)
    }




    fun onSigninButtonClicked(view: View){

//        if(R.id.btn_sign_in.toString() == "Sign up") {
//            val intent = Intent(context, MainActivity::class.java)
//            context.startActivity(intent)
//        }
        //Log.d(TAG, "onSigninButtonClicked: sign in called")
        val materialButton = view as MaterialButton
        if (!isSignIn){
            Log.e(TAG, "signInFailure: $view")
            signUp()
        } else{
            signIn()
        }
    }
    private fun signIn(){
        registrationRepo?.signIn(email, password)
    }
     fun signUp(){
         registrationRepo?.createAccount(username,email, password)
     }

    fun signInWithGoogle(){
        registrationRepo?.signInWithGoogle()
    }


    fun firebaseAuthWithGoogle(idToken: String?){
        registrationRepo?.firebaseAuthWithGoogle(idToken)
    }
}
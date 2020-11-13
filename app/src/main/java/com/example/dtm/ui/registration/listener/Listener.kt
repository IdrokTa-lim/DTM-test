package com.example.dtm.ui.registration.listener

import android.view.View
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

interface Listener {

    fun userError()
    fun emailError()
    fun passwordError()
    fun signInSuccess(user: FirebaseUser?)
    fun signInFailure(exception: Exception)
    fun createAccountSuccess(user: FirebaseUser?)
    fun createAccountFailure(exception: Exception)
    fun signInWithGoogle()

}
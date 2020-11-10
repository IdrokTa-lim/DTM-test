package com.example.dtm.repository

import android.util.Log
import com.example.dtm.registration.listener.Listener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginRepository(private val firebaseAuth: FirebaseAuth, private val listener: Listener) {

    private val TAG = "LoginRepository"

    companion object {
        private var firebaseAuth: FirebaseAuth? = null
        private var INSTANCE: LoginRepository? = null

        fun getInstance(listener: Listener): LoginRepository {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            firebaseAuth = FirebaseAuth.getInstance()
            INSTANCE = LoginRepository(firebaseAuth!!, listener)
            return INSTANCE!!
        }
    }

    fun signIn(email: String?, password: String?) {

        if (checkEmailAndPassword(email, password)) {
            Log.d(TAG, "signIn: sign in")
            firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
                .addOnSuccessListener { result ->
                    Log.d(TAG, "signIn:result: $result")
                    listener.signInSuccess(result.user)
                    if (!result.user!!.isEmailVerified){
                        result.user?.sendEmailVerification()
                    }
                }
                .addOnFailureListener { exception ->
                    listener.signInFailure(exception)
                }
        }
    }

    fun createAccount(email: String?, password: String?) {
        if (checkEmailAndPassword(email, password)) {
            firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnSuccessListener {result ->
                    listener.createAccountSuccess(result.user)
                    if (result.user != null && !result.user!!.isEmailVerified){
                        verifyEmail(result.user!!)
                    }
                }
//                .addOnCompleteListener { result ->
//                    if (result.isSuccessful) {
//                        result.addOnSuccessListener { res ->
//                            if (res.user != null) {
//                                if (res.user!!.isEmailVerified) {
//                                    listener.createAccountSuccess(res.user)
//                                } else {
//                                    verifyEmail(res.user!!)
//                                }
//                            }
//                        }
//                    } else{
//                        result.exception?.let { listener.createAccountFailure(it) }
//                    }
//                }
                .addOnFailureListener { exception ->
                    listener.createAccountFailure(exception)
                }
        }
    }

    private fun verifyEmail(user: FirebaseUser) {
        user.sendEmailVerification()
    }

    private fun checkEmailAndPassword(email: String?, password: String?): Boolean {
        var value = true

        if (email.isNullOrEmpty()) {
            listener.emailError()
            value = false
        }

        if (!password.isNullOrEmpty() && password!!.length >= 8) {
            listener.passwordError()
            value = false
        }

        return value
    }

}
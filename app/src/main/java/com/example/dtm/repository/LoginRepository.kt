package com.example.dtm.repository

import android.app.Activity
import android.util.Log
import com.example.dtm.R
import com.example.dtm.RC_SIGN_IN
import com.example.dtm.toast
import com.example.dtm.ui.registration.listener.Listener
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginRepository(
    val firebaseAuth: FirebaseAuth,
    private val listener: Listener,
    private val activity: Activity
) {

    private val TAG = "LoginRepository"
    private val gso by lazy<GoogleSignInOptions> {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    companion object {

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        private var INSTANCE: LoginRepository? = null

        fun getInstance(listener: Listener, activity: Activity): LoginRepository {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            firebaseAuth = FirebaseAuth.getInstance()
            INSTANCE = LoginRepository(firebaseAuth, listener, activity)
            return INSTANCE!!
        }
    }

    fun signIn(email: String?, password: String?) {
        if (checkForSignIn(email, password)) {
            Log.d(TAG, "signIn: sign in")
            firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
                .addOnSuccessListener { result ->
                    Log.d(TAG, "signIn:result: $result")
                    listener.signInSuccess(result.user)
                    if (!result.user!!.isEmailVerified) {
                        result.user?.sendEmailVerification()
                    }
                }
                .addOnFailureListener { exception ->
                    listener.signInFailure(exception)
                }
        }

    }

    fun createAccount(username: String?, email: String?, password: String?) {
        if (checkForSignUp(username, email, password)) {
            Log.d(TAG, "createAccount: create account open $email")
            firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnSuccessListener { result ->
                    Log.d(TAG, "createAccount: get result $result")
                    listener.createAccountSuccess(result.user)
                    if (result.user != null && !result.user!!.isEmailVerified) {
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

    private fun checkForSignIn(email: String?, password: String?): Boolean {
        var value = true


        if (email.isNullOrEmpty()) {
            listener.emailError()
            value = false
        }
        if (password.isNullOrEmpty()) {
            listener.passwordError()
            value = false
        } else {
            if (password.length < 8) {
                listener.passwordError()
                value = false
            }
        }

        return value
    }

    private fun checkForSignUp(username: String?, email: String?, password: String?): Boolean {


        var value = true
        if (!checkForSignIn(email, password)) {
            value = false
        }
        if (username.isNullOrEmpty()) {
            listener.userError()
            value = false
        }
        return value
    }

    fun signInWithGoogle() {
        Log.d(TAG, "signInWithGoogle: startActivityForResult")
        val googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signInWithFacebook(callbackManager: CallbackManager) {
        val loginManager = LoginManager.getInstance()
        loginManager.logInWithReadPermissions(activity, listOf("public_profile"));


        loginManager.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    // App code
                    activity.toast("Facebookga muvaffaqiyatli kiring")

                }

                override fun onCancel() {
                    // App code
                    activity.toast("Facebookga kirishni bekor qilish")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    listener.signInFailure(exception)
                    activity.toast("exception: $exception")
                }
            })
    }

    fun firebaseAuthWithGoogle(idToken: String?) {
        Log.d(TAG, "firebaseAuthWithGoogle: $idToken")
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    listener.signInSuccess(user)
                } else {
                    listener.signInFailure(task.exception!!)
                }

            }
    }

}
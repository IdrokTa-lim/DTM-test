package com.example.dtm.ui.registration.reg

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dtm.R
import com.example.dtm.RC_SIGN_IN
import com.example.dtm.databinding.RegistrationFragmentBinding
import com.example.dtm.toast
import com.example.dtm.ui.mainUi.MainActivity
import com.example.dtm.ui.registration.listener.Listener
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*


class RegistrationFragment : Fragment(), Listener, View.OnClickListener {


//
//    lateinit var tvHeight: ImageView
//    lateinit var tvWidth: ImageView
//
//    var display: Display = getWindowManager().getDefaultDisplay().getM
//    var width = display.width
//    var height = display.height


    private val TAG = "RegistrationFragment"
    lateinit var callbackManager: CallbackManager
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        callbackManager = CallbackManager.Factory.create()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)

        binding.google.setOnClickListener {
            viewModel.signInWithGoogle()
        }

        binding.facebook.setOnClickListener {
            viewModel.signInWithFacebook(callbackManager)
        }
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        binding.viewModel = viewModel
        binding.signUp.setOnClickListener {
            if (viewModel.isSignIn) {
                signUp()
            } else {
                signIn()
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = RegistrationViewModelFactory(findNavController(), this, requireActivity())
        viewModel = ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)
    }

    override fun userError() {
        binding.etUsername.error = "wrong usename"
    }

    override fun emailError() {
        binding.etEmail.error = "wrong email"
    }

    override fun passwordError() {
        binding.etPassword.error = "wrong password"
    }

    override fun signInSuccess(user: FirebaseUser?) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireContext().toast("sign in success ${user?.email} verified: ${user?.isEmailVerified}")
    }

    override fun signInFailure(exception: Exception) {
        if (exception is FirebaseAuthInvalidUserException) {
            requireContext().toast("user not found or user deleted")

        }
        Log.e(TAG, "signInFailure: $exception")
    }

    @SuppressLint("SetTextI18n")
    private fun signUp() {
        binding.username.visibility = View.VISIBLE
        binding.btnSignIn.text = "Registratsiya"
        binding.signIn.text = "Registratsiya"
        binding.signUp.text = "Kirish"
        viewModel.isSignIn = false
    }

    //    @SuppressLint("SetTextI18n")
    private fun signIn() {
        binding.username.visibility = View.GONE
        binding.btnSignIn.text = "Kirish"
        binding.signIn.text = "Kirish"
        binding.signUp.text = "Ro'yxatdan o'ting"
        viewModel.isSignIn = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "onActivityResult: open on activity result")
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            Log.d(TAG, "onActivityResult: $task")
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                viewModel.firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)

            }
        }


    }



    override fun createAccountSuccess(user: FirebaseUser?) {

        requireContext().toast("create account success ${user?.email}")
        val intent = Intent(context, MainActivity::class.java)
        context?.startActivity(intent)
    }

    override fun createAccountFailure(exception: Exception) {
        if (exception is FirebaseAuthUserCollisionException) {
            requireContext().toast("this email already used by another account")
        } else if (exception is FirebaseAuthInvalidCredentialsException) {
            binding.etPassword.error = "password is invalid"
        }
        Log.e(TAG, "createAccountFailure: $exception")
    }

    override fun getWindowManager(view: View) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.google -> viewModel.signInWithGoogle()

            }
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
    }


}
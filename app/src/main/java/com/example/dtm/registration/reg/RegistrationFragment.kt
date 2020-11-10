package com.example.dtm.registration.reg

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
import com.example.dtm.databinding.RegistrationFragmentBinding
import com.example.dtm.registration.listener.Listener
import com.example.dtm.toast
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser


class RegistrationFragment : Fragment(), Listener {


//
//    lateinit var tvHeight: ImageView
//    lateinit var tvWidth: ImageView
//
//    var display: Display = getWindowManager().getDefaultDisplay().getM
//    var width = display.width
//    var height = display.height
    private val TAG = "RegistrationFragment"

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel=viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = RegistrationViewModelFactory(findNavController(), this,requireContext())
        viewModel = ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)
    }

    override fun emailError() {
        binding.etEmail.error = "wrong email"
    }

    override fun passwordError() {
        binding.etPassword.error = "wrong password"
    }

    override fun signInSuccess(user: FirebaseUser?) {
        requireContext().toast("sign in success ${user?.email} verified: ${user?.isEmailVerified}")
    }

    override fun signInFailure(exception: Exception) {
        if (exception is FirebaseAuthInvalidUserException){
            requireContext().toast("user not found or user deleted")
        }
        Log.e(TAG, "signInFailure: $exception")
    }

    override fun createAccountSuccess(user: FirebaseUser?) {
        requireContext().toast("create account success ${user?.email}")
    }

    override fun createAccountFailure(exception: Exception) {
        if (exception is FirebaseAuthUserCollisionException){
            requireContext().toast("this email already used by another account")
        } else if (exception is FirebaseAuthInvalidCredentialsException){
            binding.etPassword.error = "password is invalid"
        }
        Log.e(TAG, "createAccountFailure: $exception")
    }

    override fun getWindowManager(view: View) {
        TODO("Not yet implemented")
    }

}
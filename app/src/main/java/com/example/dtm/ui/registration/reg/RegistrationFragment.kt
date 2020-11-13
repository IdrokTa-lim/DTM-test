package com.example.dtm.ui.registration.reg

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*


class RegistrationFragment : Fragment(), Listener{


    private val TAG = "RegistrationFragment"
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding
    private val gso by lazy<GoogleSignInOptions> {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client))
            .requestEmail()
            .build()
    }

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
        setAllViews()
    }

    private fun setAllViews() {

        setImageHeight()

        binding.google.setOnClickListener {
            signInWithGoogle()
        }
        binding.viewModel = viewModel
        binding.signUp.setOnClickListener {
            if (viewModel.isSignIn) {
                signUp()
            } else {
                signIn()
            }
        }

    }

    override fun signInWithGoogle() {
        Log.d(TAG, "signInWithGoogle: startActivityForResult")
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = googleSignInClient.signInIntent
        Log.d(TAG, "signInWithGoogle:intent: $signInIntent")
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun setImageHeight() {
        val screenHeight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = requireActivity().windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height()
        } else {
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }

        val params = binding.imgBg.layoutParams
        params.height = screenHeight * 4 / 6
        binding.imgBg.layoutParams = params
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
        binding.tilUsername.visibility = View.VISIBLE
        binding.btnSignIn.text = "Registratsiya"
        binding.signIn.text = "Registratsiya"
        binding.signUp.text = "Kirish"
        viewModel.isSignIn = false
    }

    //    @SuppressLint("SetTextI18n")
    private fun signIn() {

        binding.tilUsername.visibility = View.GONE
        binding.btnSignIn.text = "Kirish"
        binding.signIn.text = "Kirish"
        binding.signUp.text = "Ro'yxatdan o'ting"
        viewModel.isSignIn = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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




}
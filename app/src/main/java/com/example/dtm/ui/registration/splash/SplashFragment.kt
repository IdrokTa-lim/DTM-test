package com.example.dtm.ui.registration.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.dtm.R
import com.example.dtm.databinding.SplashFragmentBinding
import java.util.*

class SplashFragment : Fragment() {



private lateinit var timer: Timer
    private lateinit var binding:SplashFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.splash_fragment,container,false)
        return binding.root
    }

    private fun waitAndOpenOtherFragment(){
        timer= Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                findNavController().navigate(R.id.registrationFragment)
            }



        },2500)
    }

    override fun onResume() {
        super.onResume()
        waitAndOpenOtherFragment()
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

}
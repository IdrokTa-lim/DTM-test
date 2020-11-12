package com.example.dtm.ui.mainUi.onaTili

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class OnaTiliFragment : Fragment() {

    companion object {
        fun newInstance() = OnaTiliFragment()
    }

    private lateinit var viewModel: OnaTiliViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ona_tili_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OnaTiliViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
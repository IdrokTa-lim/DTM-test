package com.example.dtm.ui.mainUi.inglizTili

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class InglizTiliFragment : Fragment() {

    companion object {
        fun newInstance() = InglizTiliFragment()
    }

    private lateinit var viewModel: InglizTiliViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ingliz_tili_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InglizTiliViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
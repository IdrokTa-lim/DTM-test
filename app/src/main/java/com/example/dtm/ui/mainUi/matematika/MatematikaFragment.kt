package com.example.dtm.ui.mainUi.matematika

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class MatematikaFragment : Fragment() {

    companion object {
        fun newInstance() = MatematikaFragment()
    }

    private lateinit var viewModel: MatematikaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matematika_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatematikaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
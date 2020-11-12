package com.example.dtm.ui.mainUi.informatika

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class InformatikaFragment : Fragment() {

    companion object {
        fun newInstance() = InformatikaFragment()
    }

    private lateinit var viewModel: InformatikaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.informatika_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InformatikaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
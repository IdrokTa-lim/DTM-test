package com.example.dtm.ui.mainUi.tarix

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class TarixFragment : Fragment() {

    companion object {
        fun newInstance() = TarixFragment()
    }

    private lateinit var viewModel: TarixViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tarix_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TarixViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
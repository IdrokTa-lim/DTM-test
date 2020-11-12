package com.example.dtm.ui.mainUi.kimyo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dtm.R

class KimyoFragment : Fragment() {

    companion object {
        fun newInstance() = KimyoFragment()
    }

    private lateinit var viewModel: KimyoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.kimyo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KimyoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
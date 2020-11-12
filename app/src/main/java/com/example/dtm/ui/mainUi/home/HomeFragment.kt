package com.example.dtm.ui.mainUi.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dtm.R
import com.example.dtm.adapters.Adapter
import com.example.dtm.databinding.HomeFragmentBinding
import com.example.recyclerexample.ui.model.User
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        requireActivity().bottom_navigation.visibility=View.VISIBLE
        binding.rvMain.layoutManager = GridLayoutManager(activity,3)
        setRv()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun getList(): List<User> {



        return listOf(
            User("kimyo" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.kimyo) }!!),
            User("matematika" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.matematika) }!!),
            User("fizika" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.fizika) }!!),
            User("informatika" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.informatika) }!!),
            User("ingliz tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.english) }!!),
            User("tarix" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.history) }!!),
            User("biologiya" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.biology) }!!),
            User("ona tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.onatili) }!!),
            User("natija" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.result) }!!),
                User("ingliz tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.english) }!!),
                User("tarix" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.history) }!!),
                User("biologiya" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.biology) }!!),
                User("ona tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.onatili) }!!),
                User("natija" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.result) }!!),
                User("ingliz tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.english) }!!),
                User("tarix" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.history) }!!),
                User("biologiya" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.biology) }!!),
                User("ona tili" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.onatili) }!!),
                User("natija" , requireContext().let { ContextCompat.getDrawable(it, R.drawable.result) }!!),

            )

    }


    private fun setRv() {
        val list = getList()
        val adapter = Adapter()
        adapter.setData(list)
        binding.rvMain.layoutManager = GridLayoutManager(activity,3)
        binding.rvMain.adapter = adapter
    }
}
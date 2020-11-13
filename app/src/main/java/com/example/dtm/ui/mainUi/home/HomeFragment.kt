package com.example.dtm.ui.mainUi.home

import android.graphics.Insets.add
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.dtm.R
import com.example.dtm.adapters.Adapter
import com.example.dtm.adapters.SlideAdapter
import com.example.dtm.databinding.HomeFragmentBinding
import com.example.dtm.model.SlideItem
import com.example.dtm.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewModel: HomeViewModel
//    private val sliderRunnable= Runnable { viewpager!!.currentItem=viewpager.currentItem+1 }
//    private val sliderHandler=Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        requireActivity().bottom_navigation.visibility=View.VISIBLE
        binding.rvMain.layoutManager = GridLayoutManager(activity,3)

//        val adapter = SlideAdapter(sliderItems,viewPager2)
//        viewPager2.setAdapter(adapter)
        viewPager2 = binding.viewpager
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r + 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                sliderHandler.removeCallbacks(sliderRunnable)
//                sliderHandler.postDelayed(sliderRunnable, 3000)
//            }
//        }
//        )
        setRv()
        setImage()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setImageHeight()
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
    private fun getHorizontalList(): ArrayList<SlideItem>{
        return arrayListOf(
           SlideItem( ContextCompat.getDrawable(requireContext(), R.drawable.fizika)),
           SlideItem(ContextCompat.getDrawable(requireContext(), R.drawable.fizika)),
           SlideItem( ContextCompat.getDrawable(requireContext(), R.drawable.fizika))
        )
    }


    private fun setRv() {
        val list = getList()
        val adapter = Adapter()
        adapter.setData(list)
        binding.rvMain.layoutManager = GridLayoutManager(activity,3)
        binding.rvMain.adapter = adapter
        }
    private fun setImage(){
        val slideItem=getHorizontalList()
        val slideAdapter=SlideAdapter(slideItem ,viewPager2)
        binding.viewpager.adapter=slideAdapter


    }

    private fun setImageHeight(){
        val screenHeight=if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            val windowMetrics = requireActivity().windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height()
        }else{
            val displayMetrics=DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
        val params=binding.viewpager.layoutParams
        params.height=screenHeight*3 / 5
        binding.viewpager.layoutParams=params
    }

    override fun onPause() {
        super.onPause()
        //sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
      //  sliderHandler.postDelayed(sliderRunnable, 300000000000)
    }




}
package com.example.dtm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.dtm.R
import com.example.dtm.adapters.SlideAdapter.VH
import com.example.dtm.databinding.HorizontalPageBinding
import com.example.dtm.model.SlideItem
import com.example.dtm.model.User

 class SlideAdapter (
     private var slideItem:ArrayList<SlideItem>,
     private var viewPager2: ViewPager2,
     private var counter: Int = 0
):RecyclerView.Adapter<VH>(){


    private val runnable= Runnable {
        slideItem.addAll(slideItem)
        counter++
        if (counter > 1){
            (0..5).forEach{
                slideItem.removeFirst()
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding:HorizontalPageBinding= DataBindingUtil.inflate(inflater, R.layout.horizontal_page,parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
       holder.setImage(slideItem[position])
        if (position==slideItem.size-2){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
       return  slideItem.size
    }

     class VH(private val binding: HorizontalPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
         fun setImage(slideItem: SlideItem) {
             with(binding){

           this.imageSlide.setImageDrawable(slideItem.image)
        }


    }

    }

}
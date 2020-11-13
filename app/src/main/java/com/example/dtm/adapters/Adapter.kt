package com.example.dtm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dtm.R
import com.example.dtm.databinding.RvItemBinding
import com.example.dtm.model.User


class Adapter() : RecyclerView.Adapter<Adapter.VH>() {

    private var listUsers = listOf<User>()

    fun setData(listUsers: List<User>) {
        this.listUsers = listUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding:RvItemBinding = DataBindingUtil.inflate(inflater, R.layout.rv_item,parent,false)
       return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

//    @BindingAdapter("img")
//    fun setImageRe(view: ImageView, drawable: Drawable?) {
//        view.setImageDrawable(drawable)
//    }


    class VH(private val binding: RvItemBinding ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(user: User) {
            with(binding) {
                this.tvItem.text = user.text
                this.ivMain.setImageDrawable(user.image)

            }
        }

    }
}
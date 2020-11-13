package com.example.dtm.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.viewpager2.widget.ViewPager2


//@Entity(tableName = "user")
data class User(
    val text: String,
    val image: Drawable
)



package com.example.recyclerexample.ui.model

import android.graphics.drawable.Drawable
import androidx.room.Entity


//@Entity(tableName = "user")
data class User(
    val text: String,
    val image: Drawable
)



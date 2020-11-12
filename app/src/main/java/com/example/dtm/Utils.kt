package com.example.dtm

import android.content.Context
import android.widget.Toast



const val RC_SIGN_IN = 9001

fun Context.toast(toast:String){
    Toast.makeText(this,toast,Toast.LENGTH_SHORT).show()
}
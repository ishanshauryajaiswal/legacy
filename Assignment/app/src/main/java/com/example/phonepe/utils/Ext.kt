package com.example.phonepe.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.phonepe.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun AppCompatActivity.makeToast(s: String){
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImage(uri: String?) {
    Picasso.with(context).load(uri).placeholder(android.R.drawable.ic_menu_camera).into(this)
}
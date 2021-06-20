package com.android.classifiedd.utils

import android.widget.ImageView
import com.android.classifiedd.R
import com.squareup.picasso.Picasso

fun loadImageFromRemote(imageView : ImageView , url : String){
    Picasso.with(imageView.context).load(url).placeholder(R.drawable.loading).into(imageView)
}
fun loadDubizleLogo(imageView : ImageView){
    Picasso.with(imageView.context).load(R.drawable.dub).into(imageView)
}
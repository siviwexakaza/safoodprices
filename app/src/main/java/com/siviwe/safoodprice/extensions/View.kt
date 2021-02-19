package com.siviwe.safoodprice.extensions

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.siviwe.safoodprice.R

fun View.hide(keepSpace: Boolean = false) {
    this.visibility = if (keepSpace) View.INVISIBLE else View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.visibleOrGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.visibleOrInvisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun ImageView.loadImage(uri: String, progress: CircularProgressDrawable){
    val options = RequestOptions()
            .placeholder(progress)
            .error(R.drawable.ic_baseline_close_24)
    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
}

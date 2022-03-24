package com.camilogo1200.forums.utils

import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso

fun CircularImageView.loadImage(img: String) {
    Picasso.get().load(img).into(this);
}

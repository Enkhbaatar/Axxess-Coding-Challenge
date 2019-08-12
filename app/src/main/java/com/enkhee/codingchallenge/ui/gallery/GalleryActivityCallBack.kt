package com.enkhee.codingchallenge.ui.gallery

import android.view.View
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry

interface GalleryActivityCallBack {
    fun onItemClick(view: View, imageEntry: ImageEntry)
}
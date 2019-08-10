package com.enkhee.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry

interface GalleryRepository {
    suspend fun searchGallery(value:String):LiveData<out List<ImageEntry>>
}
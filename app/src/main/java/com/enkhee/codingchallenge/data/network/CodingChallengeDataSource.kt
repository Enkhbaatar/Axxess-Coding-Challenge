package com.enkhee.codingchallenge.data.network

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse

interface CodingChallengeDataSource {
    val downloadGallery: LiveData<GallerySearchResponse>
    fun fetchGallery(value:String)
}
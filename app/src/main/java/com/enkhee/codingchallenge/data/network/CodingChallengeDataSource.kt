package com.enkhee.codingchallenge.data.network

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.network.response.EventState
import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse
import com.enkhee.codingchallenge.internal.ObservableData
import io.reactivex.Observable

interface CodingChallengeDataSource {
    val downloadGallery: LiveData<GallerySearchResponse>
    var eventState: Observable<EventState>
    fun fetchGallery(value:String)
}
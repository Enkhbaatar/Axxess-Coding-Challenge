package com.enkhee.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.network.response.EventState
import com.enkhee.codingchallenge.internal.ObservableData
import io.reactivex.Observable

interface GalleryRepository {
    fun searchGallery(value:String):LiveData<out List<ImageEntry>>
    val eventState: Observable<EventState>
}
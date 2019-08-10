package com.enkhee.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.network.CodingChallengeDataSource
import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryRepositoryImpl(
    private val codingChallengeDataSource: CodingChallengeDataSource
) : GalleryRepository {
    private val _galleryEntries = MutableLiveData<List<ImageEntry>>()
    private val galleryEntries: LiveData<List<ImageEntry>>
        get() = _galleryEntries

    init {
        codingChallengeDataSource.downloadGallery.observeForever{newGallery ->
        persistFetchedGallery(newGallery)}
    }

    override suspend fun searchGallery(value: String): LiveData<out List<ImageEntry>> {
        return withContext(Dispatchers.IO){
            fetchGallery(value)
            return@withContext galleryEntries
        }
    }

    private fun persistFetchedGallery(fetchGallery:GallerySearchResponse){
        _galleryEntries.postValue(fetchGallery.data
            .filter { galleryEntry -> galleryEntry.imagesCount > 0 }
            .flatMap { galleryEntry -> galleryEntry.images })
    }

    private fun fetchGallery(value:String){
        codingChallengeDataSource.fetchGallery(value)
    }

}
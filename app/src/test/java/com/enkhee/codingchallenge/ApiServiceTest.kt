package com.enkhee.codingchallenge

import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ApiServiceTest {
    @Test
    fun apiServiceTest() {
        var galleryRepository = mock(GalleryRepository::class.java)
        val images = MutableLiveData<List<ImageEntry>>()
        `when`(galleryRepository.searchGallery("Good")).thenReturn(images)
    }
}
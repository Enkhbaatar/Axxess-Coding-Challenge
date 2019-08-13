package com.enkhee.codingchallenge

import android.view.View
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.repository.CommentRepository
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import com.enkhee.codingchallenge.ui.gallery.GalleryViewModel
import com.enkhee.codingchallenge.ui.image.ImageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.reflect.Method

class GalleryViewModelTest {
    @Mock
    lateinit var galleryRepository: GalleryRepository
    lateinit var galleryViewModel: GalleryViewModel

    @Before
    fun setUp() {
        galleryRepository = mock(GalleryRepository::class.java)
    }


    @Test
    fun galleryViewModelTest() {
        galleryViewModel = GalleryViewModel(galleryRepository)
        galleryViewModel.searchQuery.postValue("")
        `when`(galleryViewModel.searchQuery.postValue("Guitar")).thenAnswer {
            assertEquals(View.VISIBLE, galleryViewModel.loadingVisibility.value)
        }
    }
}
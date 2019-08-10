package com.enkhee.codingchallenge

import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.data.db.entiry.GalleryEntry
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RetrofitCallTest {
    /*@Mock
    private lateinit var galleryRepository: GalleryRepository
    @Mock
    lateinit var mockApplicationContext: Context
    @Mock
    lateinit var connectivityInterceptor: ConnectivityInterceptor
    @Mock
    lateinit var codingChallengeDataSource: CodingChallengeDataSource*/
    /*@Before
    fun setupTest(){
        MockitoAnnotations.initMocks(this)
    }*/

    @Test
    fun `searchGalleryTest`() = runBlocking {
        var galleryRepository = mock(GalleryRepository::class.java)
        val galleries = MutableLiveData<List<GalleryEntry>>()
        `when`(galleryRepository.searchGallery("")).thenReturn(galleries)
    }
}
package com.enkhee.codingchallenge

import android.view.View
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.repository.CommentRepository
import com.enkhee.codingchallenge.ui.image.ImageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.reflect.Method
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

@RunWith(JUnit4::class)
class ImageViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var commentRepository: CommentRepository

    lateinit var imageViewModel: ImageViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        imageViewModel = ImageViewModel(commentRepository)
    }

    @Test
    fun imageViewModelTest() {
        var mockImage = ImageEntry(
            "Image Title",
            "This is a Image",
            300,
            "image id",
            "url",
            300,
            300
        )
        imageViewModel.image = mockImage
        assertNotNull(imageViewModel.image)
        val method: Method = ImageViewModel::class.java.getDeclaredMethod("fetchComments")
        method.isAccessible = true
        `when`(method.invoke(imageViewModel)).then {
            assertNotNull(imageViewModel.comments)
            assertSame(0, imageViewModel.comments!!.size)
        }

        assertNotNull(imageViewModel.comment)
        assertNotNull(imageViewModel.comment.value)
        assertEquals(View.GONE, imageViewModel.loadingVisibility.value)

        `when`(imageViewModel.onClickSubmit()).then {
            assertSame(1, imageViewModel.comments!!.size)
            assertSame("", imageViewModel.comment)
        }
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}
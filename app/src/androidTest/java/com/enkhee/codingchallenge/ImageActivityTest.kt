package com.enkhee.codingchallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.enkhee.codingchallenge.data.repository.CommentRepository
import com.enkhee.codingchallenge.ui.image.ImageActivity
import com.enkhee.codingchallenge.ui.image.ImageViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric

@RunWith( AndroidJUnit4::class)
class ImageActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(ImageActivity::class.java)
    private var imageActivity: ImageActivity  = Robolectric.setupActivity(ImageActivity::class.java!!)

    @Mock
    private lateinit var commentRepository: CommentRepository
    private lateinit var imageViewModel:ImageViewModel

    @Before
    private fun setUp(){
        MockitoAnnotations.initMocks(this)
        imageViewModel = ImageViewModel(commentRepository)
        imageActivity.setTestViewModel(imageViewModel)
    }

    @Test
    fun writeComment(){
        onView(withId(R.id.et_comment)).perform(typeText(ArgumentMatchers.anyString()))
        onView(withId(R.id.btn_submit)).perform(click())
    }
}
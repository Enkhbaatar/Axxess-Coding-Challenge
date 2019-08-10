package com.enkhee.codingchallenge

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.enkhee.codingchallenge.ui.gallery.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun sayHello(){
        //Espresso.onView(ViewMatchers.withId(R.id.hello)).perform(ViewActions.typeText("Hello World!"))
        Espresso.onView(ViewMatchers.withText("Hello World!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
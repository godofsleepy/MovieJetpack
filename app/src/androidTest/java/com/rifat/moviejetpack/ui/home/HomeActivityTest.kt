package com.rifat.moviejetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    @Test
    fun loadFilm() {
        Espresso.onView(withId(R.id.list_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.list_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadVideoFilm() {
        Espresso.onView(withId(R.id.youtube_player_view1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.youtube_player_view1))
            .perform(click())
    }

    @Test
    fun loadDetailFilm() {
        Espresso.onView(withId(R.id.list_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(withText("Mortal Kombat")))
        Espresso.onView(withId(R.id.txt_date))
            .check(ViewAssertions.matches(withText("2021-04-07")))
        Espresso.onView(withId(R.id.detail_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_rate))
            .check(ViewAssertions.matches(withText("7.6")))
        Espresso.onView(withId(R.id.txt_desc))
            .check(ViewAssertions.matches(withText("Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.")))
    }

    @Test
    fun loadSeries() {
        Espresso.onView(withText("Series")).perform(click())
        Espresso.onView(withId(R.id.list_series))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.list_series))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadVideoSeries() {
        Espresso.onView(withText("Series")).perform(click())
        Espresso.onView(withId(R.id.youtube_player_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.youtube_player_view))
            .perform(click())
    }

    @Test
    fun loadDetailSeries() {
        Espresso.onView(withText("Series")).perform(click())
        Espresso.onView(withId(R.id.list_series))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(withText("Who Killed Sara?")))
        Espresso.onView(withId(R.id.txt_date))
            .check(ViewAssertions.matches(withText("2021-03-24")))
        Espresso.onView(withId(R.id.detail_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_rate))
            .check(ViewAssertions.matches(withText("7.8")))
        Espresso.onView(withId(R.id.txt_desc))
            .check(ViewAssertions.matches(withText("Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.")))
    }
}
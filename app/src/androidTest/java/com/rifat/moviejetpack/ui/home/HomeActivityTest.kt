package com.rifat.moviejetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rifat.moviejetpack.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadFilm() {
        Espresso.onView(withId(R.id.list_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.list_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadVideoFilm() {
        Espresso.onView(withId(R.id.youtube_player_view1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.youtube_player_view1))
            .perform(click())
    }

    @Test
    fun loadDetailFilm() {
        Espresso.onView(withId(R.id.list_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(withText("Mortal Kombat")))
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
        Espresso.onView(withId(R.id.youtube_player_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.youtube_player_view))
            .perform(click())
    }

    @Test
    fun loadDetailSeries() {
        Espresso.onView(withText("Series")).perform(click())
        Espresso.onView(withId(R.id.list_series))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txt_title))
            .check(ViewAssertions.matches(withText("Who Killed Sara?")))
    }
}
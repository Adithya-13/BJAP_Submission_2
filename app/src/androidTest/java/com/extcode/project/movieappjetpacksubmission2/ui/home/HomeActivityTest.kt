package com.extcode.project.movieappjetpacksubmission2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.extcode.project.movieappjetpacksubmission2.R
import com.extcode.project.movieappjetpacksubmission2.utils.DataDummy
import com.extcode.project.movieappjetpacksubmission2.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.posterTopBar)).check(matches(isDisplayed()))
        onView(withId(R.id.posterTopBar)).check(matches(withTagValue(equalTo(dummyMovies[0].posterPath))))
        onView(withId(R.id.subPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.subPoster)).check(matches(withTagValue(equalTo(dummyMovies[0].posterPath))))
        onView(withId(R.id.userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.userScore)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.nestedScrollView)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyMovies[0].overview)))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(withText("Popularity : ${dummyMovies[0].popularity}, Vote Count : ${dummyMovies[0].voteCount}, Vote Average : ${dummyMovies[0].voteAverage}")))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.posterTopBar)).check(matches(isDisplayed()))
        onView(withId(R.id.posterTopBar)).check(matches(withTagValue(equalTo(dummyTvShows[0].posterPath))))
        onView(withId(R.id.subPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.subPoster)).check(matches(withTagValue(equalTo(dummyTvShows[0].posterPath))))
        onView(withId(R.id.userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.userScore)).check(matches(withText(dummyTvShows[0].voteAverage.toString())))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail)).check(matches(withText(dummyTvShows[0].name)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyTvShows[0].firstAirDate)))
        onView(withId(R.id.nestedScrollView)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(withText("Popularity : ${dummyTvShows[0].popularity}, Vote Count : ${dummyTvShows[0].voteCount}, Vote Average : ${dummyTvShows[0].voteAverage}")))
    }
}
package se.gustavkarlsson.noted.gui.activities.notes

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.components.DaggerTestApplicationComponent
import se.gustavkarlsson.noted.di.modules.ContextModule
import se.gustavkarlsson.noted.di.modules.InMemoryDatabaseModule
import se.gustavkarlsson.noted.test.ApplicationComponentActivityTestRule
import se.gustavkarlsson.noted.test.RecyclerViewItemCountAssertion.Companion.withItemCount

@RunWith(AndroidJUnit4::class)
@LargeTest
class NotesActivityTest {

    @Suppress("unused")
    @get:Rule var activityRule = ApplicationComponentActivityTestRule(NotesActivity::class) {
        DaggerTestApplicationComponent.builder()
            .contextModule(ContextModule(NotedApplication.instance))
            .inMemoryDatabaseModule(InMemoryDatabaseModule())
            .build()
    }

    @Test
    fun addNewNote() {
        onView(withId(R.id.addButton)).perform(click())
        onView(withId(R.id.titleView)).check(matches(isDisplayed()))
        onView(withId(R.id.titleView)).perform(typeText("Topic"))
        onView(withId(R.id.contentView)).perform(typeText("Content"))
        closeSoftKeyboard()
        pressBack()
        onView(withId(R.id.notesView)).check(withItemCount(1))
    }
}

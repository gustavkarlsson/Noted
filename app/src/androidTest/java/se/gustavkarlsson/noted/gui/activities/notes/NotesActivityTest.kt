package se.gustavkarlsson.noted.gui.activities.notes

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.components.DaggerTestApplicationComponent
import se.gustavkarlsson.noted.di.components.TestApplicationComponent
import se.gustavkarlsson.noted.di.modules.ContextModule
import se.gustavkarlsson.noted.di.modules.InMemoryDatabaseModule
import se.gustavkarlsson.noted.services.database.DbNote
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.test.ApplicationComponentActivityTestRule
import se.gustavkarlsson.noted.test.RecyclerViewItemCountAssertion.Companion.withItemCount
import se.gustavkarlsson.noted.test.waitForView
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@LargeTest
class NotesActivityTest {

    @Inject
    lateinit var noteDao: NoteDao

    @Suppress("unused")
    @get:Rule
    var activityRule = ApplicationComponentActivityTestRule(NotesActivity::class) {
        DaggerTestApplicationComponent.builder()
            .contextModule(ContextModule(NotedApplication.instance))
            .inMemoryDatabaseModule(InMemoryDatabaseModule())
            .build()
    }

    @Before
    fun setUp() {
        val testComponent = NotedApplication.instance.component as TestApplicationComponent
        testComponent.inject(this)
    }

    @Test
    fun addNewNoteUpdatesNotes() {
        onView(withId(R.id.addButton)).perform(click())
        onView(withId(R.id.titleView)).perform(typeText("TODO"))
        onView(withId(R.id.contentView)).perform(typeText("stuff"))
        Espresso.closeSoftKeyboard()
        Espresso.pressBack()

        onView(withText("TODO")).check(matches(isDisplayed()))
    }

    @Test
    fun addNewNoteWithOnlyContentMakesTextContent() {
        onView(withId(R.id.addButton)).perform(click())
        onView(withId(R.id.contentView)).perform(typeText("stuff"))
        Espresso.closeSoftKeyboard()
        Espresso.pressBack()

        onView(withText("Stuff")).check(matches(isDisplayed()))
    }

    @Test
    fun addEmptyNoteDoesNothing() {
        onView(withId(R.id.addButton)).perform(click())
        Espresso.closeSoftKeyboard()
        Espresso.pressBack()

        onView(withId(R.id.notesView)).check(withItemCount(0))
    }

    @Test
    fun swipeNoteDeletesIt() {
        noteDao.insert(DbNote(1, "TODO", "stuff"))
        waitForView(withText("TODO"))

        onView(withText("TODO")).perform(swipeRight())

        onView(withId(R.id.notesView)).check(withItemCount(0))
    }

    @Test
    fun clickNoteOpensIt() {
        noteDao.insert(DbNote(1, "TODO", "stuff"))
        waitForView(withText("TODO"))

        onView(withText("TODO")).perform(click())

        onView(withId(R.id.titleView)).check(matches(isDisplayed())).check(matches(withText("TODO")))
        onView(withId(R.id.contentView)).check(matches(isDisplayed())).check(matches(withText("stuff")))
    }

    @Test
    fun clearingNoteDeletesIt() {
        noteDao.insert(DbNote(1, "TODO", "stuff"))
        waitForView(withText("TODO"))

        onView(withText("TODO")).perform(click())
        onView(withId(R.id.titleView)).perform(clearText())
        onView(withId(R.id.contentView)).perform(clearText())
        Espresso.closeSoftKeyboard()
        Espresso.pressBack()

        onView(withId(R.id.notesView)).check(withItemCount(0))
    }


}

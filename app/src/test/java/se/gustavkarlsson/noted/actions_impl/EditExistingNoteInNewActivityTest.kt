package se.gustavkarlsson.noted.actions_impl

import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.entities.ImmutableNote
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity
import se.gustavkarlsson.noted.services.ActivityStarter

@RunWith(MockitoJUnitRunner::class)
class EditExistingNoteInNewActivityTest {

    @Mock lateinit var mockActivityStarter: ActivityStarter

    lateinit var impl: EditExistingNoteInNewActivity

    @Before
    fun setUp() {
        impl = EditExistingNoteInNewActivity(mockActivityStarter)
    }

    @Test
    fun noteWithIdstartsActivity() {
        val note = ImmutableNote(1, "title", "content")
        val expectedExtras = mapOf(EditNoteActivity.NOTE_ID_EXTRA to note.id!!)

        impl(note)

        verify(mockActivityStarter).startActivity(EditNoteActivity::class, expectedExtras, null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun noteWithoutIdstartsActivityThrowsException() {
        val note = ImmutableNote(null, "title", "content")

        impl(note)
    }
}

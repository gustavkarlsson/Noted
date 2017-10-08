package se.gustavkarlsson.noted.actions_impl

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.entities.ImmutableNote
import se.gustavkarlsson.noted.services.database.NoteDao

@RunWith(MockitoJUnitRunner::class)
class SyncNoteUsingDaoTest {

    @Mock lateinit var mockNoteDao: NoteDao

    lateinit var impl: SyncNoteUsingDao

    @Before
    fun setUp() {
        impl = SyncNoteUsingDao(mockNoteDao)
    }

    @Test
    fun newEmptyNoteDoesNotTouchDao() {
        val note = ImmutableNote()

        impl(note)

        verifyZeroInteractions(mockNoteDao)
    }

    @Test
    fun newBlankNoteDoesNotTouchDao() {
        val note = ImmutableNote(null, " \t\n", " \t\n")

        impl(note)

        verifyZeroInteractions(mockNoteDao)
    }

    @Test
    fun existingEmptyNoteDeletes() {
        val note = ImmutableNote(1)

        impl(note)

        verify(mockNoteDao).delete(any())
    }

    @Test
    fun existingBlankNoteDeletes() {
        val note = ImmutableNote(1, " \t\n", " \t\n")

        impl(note)

        verify(mockNoteDao).delete(any())
    }

    @Test
    fun newNonEmptyInserts() {
        val note = ImmutableNote(null, "title", "content")

        impl(note)

        verify(mockNoteDao).insert(any())
    }

    @Test
    fun existingNonEmptyInserts() {
        val note = ImmutableNote(1, "title", "content")

        impl(note)

        verify(mockNoteDao).update(any())
    }
}

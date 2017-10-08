package se.gustavkarlsson.noted.actions_impl

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.entities.ImmutableNote
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

@RunWith(MockitoJUnitRunner::class)
class DeleteNoteUsingDaoTest {

    @Mock lateinit var mockNoteDao: NoteDao

    lateinit var note: Note

    lateinit var impl: DeleteNoteUsingDao

    @Before
    fun setUp() {
        note = ImmutableNote.EMPTY
        impl = DeleteNoteUsingDao(mockNoteDao)
    }

    @Test
    fun callsDao() {
        impl(note)

        verify(mockNoteDao).delete(any())
        verifyNoMoreInteractions(mockNoteDao)
    }
}

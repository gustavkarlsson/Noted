package se.gustavkarlsson.noted.actions_impl

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.converters.convertToEntity
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.DbNote
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.test.createSampleDbNote

@RunWith(MockitoJUnitRunner::class)
class ListNotesUsingDaoTest {

    @Suppress("unused")
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var mockNoteDao: NoteDao
    @Mock lateinit var mockObserver: Observer<List<Note>>

    lateinit var dbNote: DbNote
    lateinit var note: Note
    lateinit var liveDbNotes: MutableLiveData<List<DbNote>>

    lateinit var impl: ListNotesUsingDao

    @Before
    fun setUp() {
        dbNote = createSampleDbNote()
        note = convertToEntity(dbNote)
        liveDbNotes = MutableLiveData()
        whenever(mockNoteDao.listAll()).thenReturn(liveDbNotes)
        impl = ListNotesUsingDao(mockNoteDao)
    }

    @Test
    fun noNotesExist() {
        liveDbNotes.value = emptyList()

        val notes = impl()
        notes.observeForever(mockObserver)

        verify(mockObserver).onChanged(emptyList())
    }

    @Test
    fun oneNoteExists() {
        liveDbNotes.value = listOf(dbNote)

        val notes = impl()
        notes.observeForever(mockObserver)

        verify(mockObserver).onChanged(listOf(note))
    }

    @Test
    fun notesAreUpdatedAfterObserving() {
        liveDbNotes.value = emptyList()

        val notes = impl()
        notes.observeForever(mockObserver)
        liveDbNotes.value = listOf(dbNote)

        verify(mockObserver).onChanged(emptyList())
        verify(mockObserver).onChanged(listOf(note))
    }
}

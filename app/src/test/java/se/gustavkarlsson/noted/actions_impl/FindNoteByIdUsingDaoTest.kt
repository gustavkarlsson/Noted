package se.gustavkarlsson.noted.actions_impl

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.services.database.DbNote
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.test.createSampleDbNote

@RunWith(MockitoJUnitRunner::class)
class FindNoteByIdUsingDaoTest {

    @Mock lateinit var mockNoteDao: NoteDao

    lateinit var dbNote: DbNote

    lateinit var impl: FindNoteByIdUsingDao

    @Before
    fun setUp() {
        dbNote = createSampleDbNote()
        impl = FindNoteByIdUsingDao(mockNoteDao)
    }

    @Test
    fun dbNoteExists() {
        whenever(mockNoteDao.findById(1)).thenReturn(dbNote)

        val note = impl(1)

        assertThat(note).isNotNull()
    }

    @Test
    fun dbNoteDoesNotExist() {
        val note = impl(1)

        assertThat(note).isNull()
    }
}

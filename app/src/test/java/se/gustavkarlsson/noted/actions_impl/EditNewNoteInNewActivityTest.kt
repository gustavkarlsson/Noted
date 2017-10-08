package se.gustavkarlsson.noted.actions_impl

import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity
import se.gustavkarlsson.noted.services.ActivityStarter

@RunWith(MockitoJUnitRunner::class)
class EditNewNoteInNewActivityTest {

    @Mock
    lateinit var mockActivityStarter: ActivityStarter

    lateinit var impl: EditNewNoteInNewActivity

    @Before
    fun setUp() {
        impl = EditNewNoteInNewActivity(mockActivityStarter)
    }

    @Test
    fun startsActivity() {
        impl()

        verify(mockActivityStarter).startActivity(EditNoteActivity::class)
    }
}

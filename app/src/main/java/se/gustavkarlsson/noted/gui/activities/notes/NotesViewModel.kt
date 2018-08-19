package se.gustavkarlsson.noted.gui.activities.notes

import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.services.database.NoteDao

class NotesViewModel(
    private val store: NoteStore,
    private val dao: NoteDao
) : ViewModel() {

}

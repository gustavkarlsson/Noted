package se.gustavkarlsson.noted.gui.viewmodels.note

import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.actions.SyncNote
import se.gustavkarlsson.noted.entities.MutableNote

class NoteViewModel(
    private val sync: SyncNote,
    var note: MutableNote
) : ViewModel() {
    fun save() {
        sync(note)
    }
}

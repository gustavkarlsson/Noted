package se.gustavkarlsson.noted.gui.viewmodels.note

import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.actions.SaveNote
import se.gustavkarlsson.noted.entities.Note

class NoteViewModel(
    private val save: SaveNote,
    var note: Note
) : ViewModel() {
    fun save() {
        save(note)
    }
}

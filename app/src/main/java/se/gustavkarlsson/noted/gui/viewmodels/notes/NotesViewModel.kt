package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.actions.EditNewNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditExistingNote
import se.gustavkarlsson.noted.entities.Note

class NotesViewModel(
    val notes: LiveData<List<Note>>,
    val editNew: EditNewNote,
    val editExisting: EditExistingNote,
    val delete: DeleteNote
) : ViewModel()

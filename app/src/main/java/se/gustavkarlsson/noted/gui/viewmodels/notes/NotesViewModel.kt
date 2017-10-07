package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.actions.OpenNewNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.OpenNote
import se.gustavkarlsson.noted.entities.Note

class NotesViewModel(
    val notes: LiveData<List<Note>>,
    val openNew: OpenNewNote,
    val open: OpenNote,
    val delete: DeleteNote
) : ViewModel()

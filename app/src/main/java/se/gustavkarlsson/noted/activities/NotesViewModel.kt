package se.gustavkarlsson.noted.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.services.database.entities.Note

class NotesViewModel(
    val notes: LiveData<List<Note>>,
    val create: CreateNote,
    val edit: EditNote,
    val delete: DeleteNote
) : ViewModel()

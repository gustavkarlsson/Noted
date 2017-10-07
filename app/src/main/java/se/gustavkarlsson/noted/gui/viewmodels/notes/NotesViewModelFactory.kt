package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.actions.ListNotes


class NotesViewModelFactory(
    private val listNotes: ListNotes,
    private val createNote: CreateNote,
    private val editNote: EditNote,
    private val deleteNote: DeleteNote
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(NotesViewModel::class.java)) { "Unsupported ViewModel class: $modelClass expected: ${NotesViewModel::class}" }
        return NotesViewModel(listNotes(), createNote, editNote, deleteNote) as T
    }
}

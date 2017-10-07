package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.OpenNewNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.OpenNote
import se.gustavkarlsson.noted.actions.ListNotes
import javax.inject.Inject


class NotesViewModelFactory
@Inject
constructor(
    private val listNotes: ListNotes,
    private val openNewNote: OpenNewNote,
    private val openNote: OpenNote,
    private val deleteNote: DeleteNote
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(NotesViewModel::class.java)) { "Unsupported ViewModel class: $modelClass expected: ${NotesViewModel::class}" }
        return NotesViewModel(listNotes(), openNewNote, openNote, deleteNote) as T
    }
}

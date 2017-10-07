package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.ListNotes
import se.gustavkarlsson.noted.actions.OpenNewNote
import se.gustavkarlsson.noted.actions.OpenNote
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
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CLASS) { "Unsupported ViewModel class: $modelClass, expected: $CLASS" }
        return NotesViewModel(listNotes(), openNewNote, openNote, deleteNote) as T
    }

    companion object {
        val CLASS = NotesViewModel::class.java
    }
}

package se.gustavkarlsson.noted.gui.viewmodels.notes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.ListNotes
import se.gustavkarlsson.noted.actions.EditNewNote
import se.gustavkarlsson.noted.actions.EditExistingNote
import javax.inject.Inject


class NotesViewModelFactory
@Inject
constructor(
    private val listNotes: ListNotes,
    private val editNewNote: EditNewNote,
    private val editExistingNote: EditExistingNote,
    private val deleteNote: DeleteNote
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CLASS) { "Unsupported ViewModel class: $modelClass, expected: $CLASS" }
        return NotesViewModel(listNotes(), editNewNote, editExistingNote, deleteNote) as T
    }

    companion object {
        val CLASS = NotesViewModel::class.java
    }
}

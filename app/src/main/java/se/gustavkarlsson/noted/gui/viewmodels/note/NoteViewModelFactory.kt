package se.gustavkarlsson.noted.gui.viewmodels.note

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.SaveNote
import se.gustavkarlsson.noted.di.scopes.PerNote
import se.gustavkarlsson.noted.entities.Note
import javax.inject.Inject

@PerNote
class NoteViewModelFactory
@Inject
constructor(
    private val saveNote: SaveNote,
    private val note: Note
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == NoteViewModel::class.java) { "Unsupported ViewModel class: $modelClass expected: ${NoteViewModel::class}" }
        return NoteViewModel(saveNote, note) as T
    }
}

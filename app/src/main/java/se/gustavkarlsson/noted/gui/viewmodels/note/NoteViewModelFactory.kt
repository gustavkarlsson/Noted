package se.gustavkarlsson.noted.gui.viewmodels.note

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.gustavkarlsson.noted.actions.SyncNote
import se.gustavkarlsson.noted.di.scopes.PerNote
import se.gustavkarlsson.noted.entities.MutableNote
import se.gustavkarlsson.noted.entities.Note
import javax.inject.Inject

@PerNote
class NoteViewModelFactory
@Inject
constructor(
    private val syncNote: SyncNote,
    private val note: Note
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CLASS) { "Unsupported ViewModel class: $modelClass, expected: $CLASS" }
        return NoteViewModel(syncNote, MutableNote(note)) as T
    }

    companion object {
        val CLASS = NoteViewModel::class.java
    }
}

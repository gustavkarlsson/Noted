package se.gustavkarlsson.noted.gui.activities.notes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Maybe
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.extensions.toLiveData
import se.gustavkarlsson.noted.krate.DeleteNote
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.StartEditingNote

class NotesViewModel(
    private val store: NoteStore
) : ViewModel() {

    val notes: LiveData<List<Note>> = store.states
        .map { it.notes }
        .distinctUntilChanged()
        .toLiveData()

    val editNote: LiveData<Unit> = store.states
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.empty()
            } else {
                Maybe.just(Unit)
            }
        }
        .distinctUntilChanged()
        .toLiveData()

    fun createNewNote() = store.issue(StartEditingNote(Note()))

    fun editNote(note: Note) = store.issue(StartEditingNote(note))

    fun deleteNote(note: Note) = store.issue(DeleteNote(note))
}

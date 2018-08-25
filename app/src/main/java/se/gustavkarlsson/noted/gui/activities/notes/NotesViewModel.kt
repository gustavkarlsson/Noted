package se.gustavkarlsson.noted.gui.activities.notes

import io.reactivex.Flowable
import io.reactivex.Maybe
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.krate.DeleteNote
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.StartEditingNote

class NotesViewModel(private val store: NoteStore) {

    val notes: Flowable<List<Note>> = store.states
        .map { it.notes }
        .distinctUntilChanged()

    val editNote: Flowable<Unit> = store.states
        .distinctUntilChanged { a, b -> a.editingNote == b.editingNote }
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.empty()
            } else {
                Maybe.just(Unit)
            }
        }

    fun createNewNote() = store.issue(StartEditingNote(Note()))

    fun editNote(note: Note) = store.issue(StartEditingNote(note))

    fun deleteNote(note: Note) = store.issue(DeleteNote(note))
}

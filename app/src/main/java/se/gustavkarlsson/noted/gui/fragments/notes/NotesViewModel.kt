package se.gustavkarlsson.noted.gui.fragments.notes

import io.reactivex.Flowable
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.krate.DeleteNote
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.StartEditingNote

class NotesViewModel(private val store: NoteStore) {

    val notes: Flowable<List<Note>> = store.states
        .map { it.notes }
        .distinctUntilChanged()

    fun createNewNote() = store.issue(StartEditingNote(Note()))

    fun editNote(note: Note) = store.issue(StartEditingNote(note))

    fun deleteNote(note: Note) = store.issue(DeleteNote(note))
}

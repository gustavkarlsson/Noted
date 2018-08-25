package se.gustavkarlsson.noted.gui.fragments.editnote

import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.SetEditingNoteContent
import se.gustavkarlsson.noted.krate.SetEditingNoteTitle

class EditNoteViewModel(private val store: NoteStore) {

    val initialTitle: String = store.currentState.editingNote!!.title

    val initialContent: String = store.currentState.editingNote!!.content

    fun setTitle(text: CharSequence) {
        store.issue(SetEditingNoteTitle(text.toString()))
    }

    fun setContent(text: CharSequence) {
        store.issue(SetEditingNoteContent(text.toString()))
    }

}

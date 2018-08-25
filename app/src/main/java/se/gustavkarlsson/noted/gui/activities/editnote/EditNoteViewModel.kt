package se.gustavkarlsson.noted.gui.activities.editnote

import io.reactivex.Maybe
import io.reactivex.Single
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.SetEditingNoteContent
import se.gustavkarlsson.noted.krate.SetEditingNoteTitle
import se.gustavkarlsson.noted.krate.StopEditingNote

class EditNoteViewModel(private val store: NoteStore) {

    val initialTitle: String = store.currentState.editingNote!!.title

    val initialContent: String = store.currentState.editingNote!!.content

    val goBack: Single<Unit> = store.states
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.just(Unit)
            } else {
                Maybe.empty()
            }
        }.firstOrError()

    fun setTitle(text: CharSequence) {
        store.issue(SetEditingNoteTitle(text.toString()))
    }

    fun setContent(text: CharSequence) {
        store.issue(SetEditingNoteContent(text.toString()))
    }

    fun stopEditingNote() {
        store.issue(StopEditingNote)
    }

}

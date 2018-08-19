package se.gustavkarlsson.noted.gui.activities.editnote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Maybe
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.extensions.toLiveData
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.SetEditingNoteContent
import se.gustavkarlsson.noted.krate.SetEditingNoteTitle
import se.gustavkarlsson.noted.krate.StopEditingNote

class EditNoteViewModel(
    private val store: NoteStore
) : ViewModel() {

    private val editingNote = store.states
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.empty()
            } else {
                Maybe.just(it.editingNote)
            }
        }

    val title: LiveData<String> = editingNote
        .map(Note::title)
        .distinctUntilChanged()
        .toLiveData()

    val content: LiveData<String> = editingNote
        .map(Note::content)
        .distinctUntilChanged()
        .toLiveData()

    val goBack: LiveData<Unit> = store.states
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.just(Unit)
            } else {
                Maybe.empty()
            }
        }
        .toLiveData()

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

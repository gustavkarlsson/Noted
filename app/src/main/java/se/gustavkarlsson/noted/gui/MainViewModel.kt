package se.gustavkarlsson.noted.gui

import io.reactivex.Flowable
import io.reactivex.Maybe
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.StopEditingNote

class MainViewModel(private val store: NoteStore) {

    val navigateToEditNote: Flowable<Unit> = store.states
        .distinctUntilChanged { a, b ->
            (a.editingNote != null) == (b.editingNote != null)
        }
        .flatMapMaybe {
            if (it.editingNote == null) {
                Maybe.empty()
            } else {
                Maybe.just(Unit)
            }
        }

    fun onNotNavigatingToEditNote() {
        store.issue(StopEditingNote)
    }
}

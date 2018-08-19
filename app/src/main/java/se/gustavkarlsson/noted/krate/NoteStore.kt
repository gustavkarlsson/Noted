package se.gustavkarlsson.noted.krate

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import se.gustavkarlsson.krate.core.Store
import se.gustavkarlsson.krate.core.dsl.buildStore
import se.gustavkarlsson.noted.converters.toDb
import se.gustavkarlsson.noted.converters.toEntity
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.DbNote
import se.gustavkarlsson.noted.services.database.NoteDao

typealias NoteStore = Store<State, Command, Result>

fun buildStore(dao: NoteDao): NoteStore = buildStore {

    states {
        initial = State()
        observeScheduler = AndroidSchedulers.mainThread()
    }

    commands {
        transform<StreamNotes> { commands ->
            commands
                .distinctUntilChanged()
                .observeOn(Schedulers.io())
                .flatMap { dao.listAll() }
                .map { it.map(DbNote::toEntity) }
                .map(::Notes)
        }

        transform<StartEditingNote> { commands ->
            commands
                .map(StartEditingNote::note)
                .map(::SetEditingNote)
        }

        transformWithState<SetEditingNoteTitle> { commands, getState ->
            commands
                .map(SetEditingNoteTitle::text)
                .flatMapMaybe { newTitle ->
                    val editingNote = getState().editingNote
                    if (editingNote == null) {
                        Maybe.empty()
                    } else {
                        val newNote = editingNote.copy(title = newTitle)
                        Maybe.just(SetEditingNote(newNote))
                    }
                }
        }

        transformWithState<SetEditingNoteContent> { commands, getState ->
            commands
                .map(SetEditingNoteContent::text)
                .flatMapMaybe { newContent ->
                    val editingNote = getState().editingNote
                    if (editingNote == null) {
                        Maybe.empty()
                    } else {
                        val newNote = editingNote.copy(content = newContent)
                        Maybe.just(SetEditingNote(newNote))
                    }
                }
        }

        transformWithState<StopEditingNote> { commands, getState ->
            commands
                .observeOn(Schedulers.io())
                .doOnNext {
                    getState().editingNote?.let { editingNote ->
                        if (editingNote.title.isBlank() && editingNote.content.isBlank()) {
                            if (editingNote.id != null) {
                                dao.delete(editingNote.toDb())
                            }
                        } else {
                            if (editingNote.id != null) {
                                dao.update(editingNote.toDb())
                            } else {
                                dao.insert(editingNote.toDb())
                            }
                        }
                    }
                }
                .map { SetEditingNote(null) }
        }

        transform<DeleteNote> { commands ->
            commands
                .map(DeleteNote::note)
                .map(Note::toDb)
                .observeOn(Schedulers.io())
                .doOnNext(dao::delete)
                .flatMap<Result> { Flowable.empty() }
        }
    }

    results {
        reduce<Notes> { state, (notes) ->
            state.copy(notes = notes)
        }

        reduce<SetEditingNote> { state, (note) ->
            state.copy(editingNote = note)
        }
    }
}

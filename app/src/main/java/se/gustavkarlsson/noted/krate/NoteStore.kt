package se.gustavkarlsson.noted.krate

import io.reactivex.Flowable
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
    }

    commands {
        transform<StreamNotes> { commands ->
            commands
                .distinctUntilChanged()
                .flatMap { dao.listAll() }
                .map { it.map(DbNote::toEntity) }
                .map(::Notes)
        }

        transform<StartEditingNote> { commands ->
            commands
                .map(StartEditingNote::note)
                .map(::SetEditingNote)
        }

        transform<StopEditingNote> { commands ->
            commands
                .map { SetEditingNote(null) }
        }

        transform<DeleteNote> { commands ->
            commands
                .map(DeleteNote::note)
                .map(Note::toDb)
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

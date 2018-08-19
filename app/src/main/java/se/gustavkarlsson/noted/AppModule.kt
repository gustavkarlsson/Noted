package se.gustavkarlsson.noted

import android.arch.persistence.room.Room
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteViewModel
import se.gustavkarlsson.noted.gui.activities.notes.NotesViewModel
import se.gustavkarlsson.noted.krate.store
import se.gustavkarlsson.noted.services.database.NotedDatabase

val appModule = module {

    single { store }

    single {
        Room.databaseBuilder(get(), NotedDatabase::class.java, "notes")
            .build()
            .getNoteDao()
    }

    viewModel { EditNoteViewModel(get(), get()) }

    viewModel { NotesViewModel(get(), get()) }
}

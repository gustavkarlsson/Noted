package se.gustavkarlsson.noted

import androidx.room.Room
import org.koin.dsl.module.module
import se.gustavkarlsson.noted.gui.MainViewModel
import se.gustavkarlsson.noted.gui.fragments.editnote.EditNoteViewModel
import se.gustavkarlsson.noted.gui.fragments.notes.NotesViewModel
import se.gustavkarlsson.noted.krate.buildStore
import se.gustavkarlsson.noted.services.database.NotedDatabase

val appModule = module {

    single { buildStore(get()) }

    single {
        Room.databaseBuilder(get(), NotedDatabase::class.java, "notes")
            .build()
            .getNoteDao()
    }

    factory { EditNoteViewModel(get()) }

    factory { NotesViewModel(get()) }

    factory { MainViewModel(get()) }
}

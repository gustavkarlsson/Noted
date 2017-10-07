package se.gustavkarlsson.noted.di.components

import dagger.Component
import se.gustavkarlsson.noted.di.modules.ContextModule
import se.gustavkarlsson.noted.di.modules.DatabaseModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ContextModule::class,
        DatabaseModule::class
))
interface ApplicationComponent {
    val notesActivityComponent: NotesActivityComponent
    val editNoteActivityComponent: EditNoteActivityComponent
}

package se.gustavkarlsson.noted.di.components

import dagger.Component
import se.gustavkarlsson.noted.di.modules.ContextModule
import se.gustavkarlsson.noted.di.modules.InMemoryDatabaseModule
import se.gustavkarlsson.noted.gui.activities.notes.NotesActivityTest
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ContextModule::class,
        InMemoryDatabaseModule::class
))
interface TestApplicationComponent : ApplicationComponent {
    fun inject(notesActivityTest: NotesActivityTest)
}

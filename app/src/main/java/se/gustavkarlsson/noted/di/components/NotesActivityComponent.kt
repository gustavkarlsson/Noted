package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.gui.activities.NotesActivity
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.di.modules.NoteActionsModule
import se.gustavkarlsson.noted.di.modules.NotesViewModelModule
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Subcomponent(modules = arrayOf(
    ActivityModule::class,
    NoteActionsModule::class,
    NotesViewModelModule::class
))
@PerActivity
interface NotesActivityComponent {
    fun inject(activity: NotesActivity)
}

package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.activities.NotesActivity
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.di.modules.NoteActionsModule
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Subcomponent(modules = arrayOf(
    ActivityModule::class,
    NoteActionsModule::class
))
@PerActivity
interface NotesActivityComponent {
    fun inject(activity: NotesActivity)
}

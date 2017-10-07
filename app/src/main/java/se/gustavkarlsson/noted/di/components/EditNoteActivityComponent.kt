package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.di.modules.NoteActionsModule
import se.gustavkarlsson.noted.di.scopes.PerActivity
import se.gustavkarlsson.noted.gui.activities.EditNoteActivity

@Subcomponent(modules = arrayOf(
    ActivityModule::class,
    NoteActionsModule::class
))
@PerActivity
interface EditNoteActivityComponent {
    fun inject(activity: EditNoteActivity)
}

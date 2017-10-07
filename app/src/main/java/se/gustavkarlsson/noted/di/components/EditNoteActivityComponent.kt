package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.di.modules.CurrentNoteFromIntentModule
import se.gustavkarlsson.noted.di.modules.NoteActionsModule
import se.gustavkarlsson.noted.di.modules.NoteViewModelModule
import se.gustavkarlsson.noted.di.scopes.PerActivity
import se.gustavkarlsson.noted.di.scopes.PerNote
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity

@Subcomponent(modules = arrayOf(
    ActivityModule::class,
    NoteActionsModule::class,
    CurrentNoteFromIntentModule::class,
    NoteViewModelModule::class
))
@PerActivity
@PerNote
interface EditNoteActivityComponent {
    fun inject(activity: EditNoteActivity)
}

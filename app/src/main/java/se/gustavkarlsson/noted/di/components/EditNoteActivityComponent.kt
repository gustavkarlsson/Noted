package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.gui.activities.EditNoteActivity
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Subcomponent
@PerActivity
interface EditNoteActivityComponent {
    fun inject(activity: EditNoteActivity)
}

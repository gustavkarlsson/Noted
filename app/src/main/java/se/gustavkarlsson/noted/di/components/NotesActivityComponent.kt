package se.gustavkarlsson.noted.di.components

import dagger.Subcomponent
import se.gustavkarlsson.noted.activities.NotesActivity
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Subcomponent
@PerActivity
interface NotesActivityComponent {
    fun inject(activity: NotesActivity)
}

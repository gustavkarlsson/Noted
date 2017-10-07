package se.gustavkarlsson.noted.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import se.gustavkarlsson.noted.actions.FindNoteById
import se.gustavkarlsson.noted.di.scopes.PerNote
import se.gustavkarlsson.noted.entities.ImmutableNote
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity

@Module
class CurrentNoteFromIntentModule {

    @Provides
    @PerNote
    fun provideCurrentNote(activity: Activity, findNoteById: FindNoteById): Note {
        val noteId = activity.intent.getLongExtra(EditNoteActivity.NOTE_ID_EXTRA, 0)
        if (noteId == 0L) {
            return ImmutableNote.EMPTY
        }
        return findNoteById(noteId) ?: ImmutableNote.EMPTY
    }
}

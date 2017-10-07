package se.gustavkarlsson.noted.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.actions_impl.CreateNoteInActivity
import se.gustavkarlsson.noted.actions_impl.DeleteNoteUsingDao
import se.gustavkarlsson.noted.actions_impl.EditNoteInActivity
import se.gustavkarlsson.noted.services.database.NoteDao

@Module
class NoteActionsModule {

    @Provides
    @Reusable
    fun provideCreateNote(activity: Activity): CreateNote = CreateNoteInActivity(activity)

    @Provides
    @Reusable
    fun provideEditNote(activity: Activity): EditNote = EditNoteInActivity(activity)

    @Provides
    @Reusable
    fun provideDeleteNote(noteDao: NoteDao): DeleteNote = DeleteNoteUsingDao(noteDao)
}

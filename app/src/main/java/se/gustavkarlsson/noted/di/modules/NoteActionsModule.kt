package se.gustavkarlsson.noted.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.actions.*
import se.gustavkarlsson.noted.actions_impl.*
import se.gustavkarlsson.noted.services.database.NoteDao

@Module
class NoteActionsModule {

    @Provides
    @Reusable
    fun provideListNotes(noteDao: NoteDao): ListNotes = ListNotesUsingDao(noteDao)

    @Provides
    @Reusable
    fun provideCreateNote(activity: Activity): OpenNewNote = OpenNewNoteInActivity(activity)

    @Provides
    @Reusable
    fun provideEditNote(activity: Activity): OpenNote = OpenNoteInActivity(activity)

    @Provides
    @Reusable
    fun provideDeleteNote(noteDao: NoteDao): DeleteNote = DeleteNoteUsingDao(noteDao)

    @Provides
    @Reusable
    fun provideFindNoteById(noteDao: NoteDao): FindNoteById = FindNoteByIdUsingDao(noteDao)

    @Provides
    @Reusable
    fun provideSaveNote(noteDao: NoteDao): SyncNote = SyncNoteUsingDao(noteDao)
}

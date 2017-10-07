package se.gustavkarlsson.noted.di.modules

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.actions.ListNotes
import se.gustavkarlsson.noted.activities.NotesViewModel
import se.gustavkarlsson.noted.activities.NotesViewModelFactory


@Module
class NotesViewModelModule {

    @Provides
    @Reusable
    fun provideNotesViewModelFactory(listNotes: ListNotes, create: CreateNote, edit: EditNote, delete: DeleteNote): NotesViewModelFactory = NotesViewModelFactory(listNotes, create, edit, delete)

    @Provides
    @Reusable
    fun provideNotesViewModel(activity: FragmentActivity, factory: NotesViewModelFactory): NotesViewModel = ViewModelProviders.of(activity, factory).get(NotesViewModel::class.java)
}

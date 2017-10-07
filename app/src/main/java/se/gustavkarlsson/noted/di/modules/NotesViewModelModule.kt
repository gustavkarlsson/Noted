package se.gustavkarlsson.noted.di.modules

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.gui.viewmodels.notes.NotesViewModel
import se.gustavkarlsson.noted.gui.viewmodels.notes.NotesViewModelFactory


@Module
class NotesViewModelModule {

    @Provides
    @Reusable
    fun provideNotesViewModel(activity: FragmentActivity, factory: NotesViewModelFactory): NotesViewModel = ViewModelProviders.of(activity, factory).get(NotesViewModel::class.java)
}

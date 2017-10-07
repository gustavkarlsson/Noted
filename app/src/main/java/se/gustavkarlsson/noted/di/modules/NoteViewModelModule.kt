package se.gustavkarlsson.noted.di.modules

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.gui.viewmodels.note.NoteViewModel
import se.gustavkarlsson.noted.gui.viewmodels.note.NoteViewModelFactory

@Module
class NoteViewModelModule {

    @Provides
    @Reusable
    fun provideNoteViewModel(activity: FragmentActivity, factory: NoteViewModelFactory): NoteViewModel = ViewModelProviders.of(activity, factory).get(NoteViewModel::class.java)
}

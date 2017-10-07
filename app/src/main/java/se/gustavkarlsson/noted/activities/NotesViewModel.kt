package se.gustavkarlsson.noted.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import se.gustavkarlsson.noted.services.database.entities.Note

class NotesViewModel : ViewModel() {
    lateinit var notes: LiveData<List<Note>>
        private set

    fun load(notes: LiveData<List<Note>>) {
        this.notes = notes
    }
}

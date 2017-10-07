package se.gustavkarlsson.noted.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.services.database.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

class NotesViewModel : ViewModel() {
    private lateinit var noteDao: NoteDao

    lateinit var notes: LiveData<List<Note>>
        private set

    fun init(noteDao: NoteDao) {
        this.noteDao = noteDao
        notes = noteDao.listAll()
    }

    fun onCreate(note: Note) {
        bg {
            noteDao.insert(note)
        }
    }

    fun onDelete(note: Note) {
        bg {
            noteDao.delete(note)
        }
    }
}

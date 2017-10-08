package se.gustavkarlsson.noted.actions_impl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import se.gustavkarlsson.noted.actions.ListNotes
import se.gustavkarlsson.noted.converters.convertToEntity
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.DbNote
import se.gustavkarlsson.noted.services.database.NoteDao

class ListNotesUsingDao(private val noteDao: NoteDao) : ListNotes {
    override fun invoke(): LiveData<List<Note>> {
        return Transformations.map(noteDao.listAll(), this::convertToEntities)
    }

    private fun convertToEntities(dbNotes: List<DbNote>): List<Note> {
        return dbNotes.map { convertToEntity(it) }
    }
}

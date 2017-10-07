package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.FindNoteById
import se.gustavkarlsson.noted.converters.convertToEntity
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

class FindNoteByIdUsingDao(private val noteDao: NoteDao) : FindNoteById {
    override fun invoke(id: Long): Note? {
        return noteDao.findById(id)?.let { convertToEntity(it) }
    }
}
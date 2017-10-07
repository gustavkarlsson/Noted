package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.converters.convertToDb
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

class DeleteNoteUsingDao(private val noteDao: NoteDao) : DeleteNote {
    override fun invoke(note: Note) = noteDao.delete(convertToDb(note))
}

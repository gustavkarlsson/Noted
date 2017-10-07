package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.entities.Note

class DeleteNoteUsingDao(private val noteDao: NoteDao) : DeleteNote {
    override fun invoke(note: Note) = noteDao.delete(note)
}

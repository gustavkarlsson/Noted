package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.SyncNote
import se.gustavkarlsson.noted.converters.convertToDb
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

class SyncNoteUsingDao(private val noteDao: NoteDao) : SyncNote {
    override fun invoke(note: Note) {
        note.run {
            if (isNew() && isBlank()) {
            } else if (isNew() && !isBlank()) {
                noteDao.insert(convertToDb(note))
            } else if (!isNew() && isBlank()) {
                noteDao.delete(convertToDb(note))
            } else if (!isNew() && !isBlank()) {
                noteDao.update(convertToDb(note))
            } else {
                throw IllegalStateException("Don't know what to do with note: $this")
            }
        }
    }

    private fun Note.isNew() = id == null
    private fun Note.isBlank() = title.isBlank() && content.isBlank()
}

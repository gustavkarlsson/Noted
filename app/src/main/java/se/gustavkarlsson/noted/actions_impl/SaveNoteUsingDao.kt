package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.SaveNote
import se.gustavkarlsson.noted.converters.convertToDb
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao

class SaveNoteUsingDao(private val noteDao: NoteDao) : SaveNote {
    override fun invoke(note: Note) {
        val dbNote = convertToDb(note)
        if (dbNote.id == 0L) {
            noteDao.insert(dbNote)
        } else {
            noteDao.update(dbNote)
        }
    }
}

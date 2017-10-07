package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.ListNotes
import se.gustavkarlsson.noted.services.database.NoteDao

class ListNotesUsingDao(private val noteDao: NoteDao) : ListNotes {
    override fun invoke() = noteDao.listAll()
}

package se.gustavkarlsson.noted.converters

import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.DbNote

fun DbNote.toEntity(): Note = Note(id, title, content)

fun Note.toDb(): DbNote = DbNote(id ?: 0, title, content)

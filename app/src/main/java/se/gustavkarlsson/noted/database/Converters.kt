package se.gustavkarlsson.noted.database

import se.gustavkarlsson.noted.domain.Note

fun DbNote.toEntity(): Note = Note(id, title, content)

fun Note.toDb(): DbNote = DbNote(id ?: 0, title, content)

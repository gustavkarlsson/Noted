package se.gustavkarlsson.noted.converters

import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.services.database.DbNote

fun convertToEntity(dbNote: DbNote): Note = Note(dbNote.id, dbNote.title, dbNote.content)

fun convertToDb(dbNote: Note): DbNote = DbNote(dbNote.id ?: 0, dbNote.title, dbNote.content)

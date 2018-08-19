package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.noted.entities.Note

sealed class Result

data class Notes(val notes: List<Note>) : Result()

data class SetEditingNote(val note: Note?) : Result()

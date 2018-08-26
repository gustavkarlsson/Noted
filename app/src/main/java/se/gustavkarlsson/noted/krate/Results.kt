package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.noted.domain.Note

sealed class Result

data class Notes(val notes: List<Note>) : Result()

data class SetEditingNote(val note: Note?) : Result()

data class ChangeEditingNote(val title: String? = null, val content: String? = null) : Result()

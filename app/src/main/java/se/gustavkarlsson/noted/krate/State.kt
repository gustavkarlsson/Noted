package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.noted.domain.Note

data class State(val notes: List<Note> = emptyList(), val editingNote: Note? = null)

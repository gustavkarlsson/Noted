package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.noted.entities.Note

data class State(val notes: List<Note> = emptyList(), val editingNote: Note?)

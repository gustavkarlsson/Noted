package se.gustavkarlsson.noted.krate

import se.gustavkarlsson.noted.entities.Note

sealed class Command

object StreamNotes : Command()

data class EditNote(val note: Note) : Command()

data class DeleteNote(val note: Note) : Command()

object StopEditingNote : Command()

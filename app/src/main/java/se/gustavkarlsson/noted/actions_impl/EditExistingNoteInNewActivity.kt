package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.EditExistingNote
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity
import se.gustavkarlsson.noted.services.ActivityStarter

class EditExistingNoteInNewActivity(
    private val activityStarter: ActivityStarter
) : EditExistingNote {
    override fun invoke(note: Note) {
        require(note.id != null) { "Note id may not be null" }
        val extras = mapOf(EditNoteActivity.NOTE_ID_EXTRA to note.id!!)
        activityStarter.startActivity(EditNoteActivity::class, extras)
    }
}

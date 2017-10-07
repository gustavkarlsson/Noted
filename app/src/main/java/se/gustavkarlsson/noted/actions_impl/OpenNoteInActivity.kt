package se.gustavkarlsson.noted.actions_impl

import android.app.Activity
import android.content.Intent
import se.gustavkarlsson.noted.actions.OpenNote
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity

class OpenNoteInActivity(private val sourceActivity: Activity) : OpenNote {
    override fun invoke(note: Note) {
        val editNoteIntent = Intent(sourceActivity, EditNoteActivity::class.java)
        editNoteIntent.putExtra(EditNoteActivity.NOTE_ID_EXTRA, note.id)
        sourceActivity.startActivity(editNoteIntent)
    }
}

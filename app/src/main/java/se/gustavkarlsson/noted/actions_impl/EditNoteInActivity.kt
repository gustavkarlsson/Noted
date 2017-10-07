package se.gustavkarlsson.noted.actions_impl

import android.app.Activity
import android.content.Intent
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.activities.EditNoteActivity
import se.gustavkarlsson.noted.services.database.entities.Note

class EditNoteInActivity(private val sourceActivity: Activity) : EditNote {
    override fun invoke(note: Note) {
        val editNoteIntent = Intent(sourceActivity, EditNoteActivity::class.java)
        editNoteIntent.putExtra(EditNoteActivity.NOTE_ID_EXTRA, note.id)
        sourceActivity.startActivity(editNoteIntent)
    }
}

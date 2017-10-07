package se.gustavkarlsson.noted.actions_impl

import android.app.Activity
import android.content.Intent
import se.gustavkarlsson.noted.actions.OpenNewNote
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity

class OpenNewNoteInActivity(private val sourceActivity: Activity) : OpenNewNote {
    override fun invoke() {
        val editNoteIntent = Intent(sourceActivity, EditNoteActivity::class.java)
        sourceActivity.startActivity(editNoteIntent)
    }
}

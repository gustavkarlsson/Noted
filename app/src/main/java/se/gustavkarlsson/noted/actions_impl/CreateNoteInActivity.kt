package se.gustavkarlsson.noted.actions_impl

import android.app.Activity
import android.content.Intent
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.gui.activities.EditNoteActivity

class CreateNoteInActivity(private val sourceActivity: Activity) : CreateNote {
    override fun invoke() {
        val editNoteIntent = Intent(sourceActivity, EditNoteActivity::class.java)
        sourceActivity.startActivity(editNoteIntent)
    }
}

package se.gustavkarlsson.noted.actions_impl

import se.gustavkarlsson.noted.actions.EditNewNote
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity
import se.gustavkarlsson.noted.services.ActivityStarter

class EditNewNoteInNewActivity(
    private val activityStarter: ActivityStarter
) : EditNewNote {
    override fun invoke() {
        activityStarter.startActivity(EditNoteActivity::class)
    }
}

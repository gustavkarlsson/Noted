package se.gustavkarlsson.noted.gui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.actions.FindNoteById
import se.gustavkarlsson.noted.actions.SaveNote
import se.gustavkarlsson.noted.entities.Note
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {

    @Inject
    lateinit var findNoteById: FindNoteById

    @Inject
    lateinit var saveNote: SaveNote

    private var noteId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent
            .getEditNoteActivityComponent()
            .inject(this)
        setContentView(R.layout.activity_note)

        if (intent.hasExtra(NOTE_ID_EXTRA)) {
            noteId = intent.getLongExtra(NOTE_ID_EXTRA, -1)
            if (noteId == -1L) {
                noteId = null
            }
        }

        if (noteId != null) {
            bg {
                val note = findNoteById(noteId!!)
                if (note != null) {
                    titleView.setText(note.title, TextView.BufferType.EDITABLE)
                    contentView.setText(note.content, TextView.BufferType.EDITABLE)
                }
            }
        }

        saveButton.setOnClickListener {
            bg {
                val note = if (noteId == null) {
                    Note(0, titleView.text.toString(), contentView.text.toString())
                } else {
                    Note(noteId!!, titleView.text.toString(), contentView.text.toString())
                }
                saveNote(note)
                finish()
            }
        }
    }

    companion object {
        const val NOTE_ID_EXTRA = "noteId"
    }
}

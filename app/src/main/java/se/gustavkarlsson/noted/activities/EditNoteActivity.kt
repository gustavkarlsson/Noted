package se.gustavkarlsson.noted.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.services.database.entities.Note
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {

    @Inject
    lateinit var noteDao: NoteDao

    var noteId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent.editNoteActivityComponent.inject(this)
        setContentView(R.layout.activity_note)

        if (intent.hasExtra("noteId")) {
            noteId = intent.getIntExtra("noteId", -1)
            if (noteId == -1) {
                noteId = null
            }
        }

        if (noteId != null) {
            bg {
                val note = noteDao.findById(noteId!!)
                if (note != null) {
                    titleView.setText(note.title, TextView.BufferType.EDITABLE)
                    contentView.setText(note.content, TextView.BufferType.EDITABLE)
                }
            }
        }

        saveButton.setOnClickListener {
            bg {
                if (noteId == null) {
                    val note = Note(0, titleView.text.toString(), contentView.text.toString())
                    noteDao.insert(note)
                } else {
                    val note = Note(noteId!!, titleView.text.toString(), contentView.text.toString())
                    noteDao.update(note)
                }
                finish()
            }
        }
    }
}

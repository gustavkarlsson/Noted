package se.gustavkarlsson.noted.gui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.viewmodels.note.NoteViewModel
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {

    @Inject
    lateinit var noteModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent
            .getEditNoteActivityComponent(ActivityModule(this))
            .inject(this)
        setContentView(R.layout.activity_note)
        bindData()
    }

    private fun bindData() {
        titleView.setText(noteModel.note.title, TextView.BufferType.EDITABLE)
        contentView.setText(noteModel.note.content, TextView.BufferType.EDITABLE)

        titleView.addTextChangedListener(UpdateNoteTextWatcher(noteModel, { note, newText -> note.copy(title = newText) }))

        contentView.addTextChangedListener(UpdateNoteTextWatcher(noteModel, { note, newText -> note.copy(content = newText) }))

        saveButton.setOnClickListener {
            bg {
                noteModel.save()
                finish()
            }
        }
    }

    private class UpdateNoteTextWatcher(private val noteModel: NoteViewModel, private val update: (Note, String) -> Note) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable) {
            noteModel.note = update(noteModel.note, s.toString())
        }

    }

    companion object {
        const val NOTE_ID_EXTRA = "noteId"
    }
}

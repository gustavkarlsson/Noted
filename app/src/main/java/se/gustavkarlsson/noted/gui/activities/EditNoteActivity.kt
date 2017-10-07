package se.gustavkarlsson.noted.gui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.gui.viewmodels.note.NoteViewModel
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {

    @Inject
    lateinit var noteModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this;
        runBlocking(CommonPool) {
            NotedApplication.applicationComponent
                .getEditNoteActivityComponent(ActivityModule(activity))
                .inject(activity)
        }
        setContentView(R.layout.activity_note)

        titleView.setText(noteModel.note.title, TextView.BufferType.EDITABLE)
        contentView.setText(noteModel.note.content, TextView.BufferType.EDITABLE)

        titleView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                noteModel.note = noteModel.note.copy(title = s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        contentView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                noteModel.note = noteModel.note.copy(content = s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        saveButton.setOnClickListener {
            bg {
                noteModel.save()
                finish()
            }
        }
    }

    companion object {
        const val NOTE_ID_EXTRA = "noteId"
    }
}

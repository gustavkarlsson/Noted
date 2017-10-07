package se.gustavkarlsson.noted.gui.activities.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
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
        NotedApplication.applicationComponent
            .getEditNoteActivityComponent(ActivityModule(this))
            .inject(this)
        setContentView(R.layout.activity_note)
        bindData()
    }

    private fun bindData() {
        bindViewToModel(titleView, noteModel.note.mutableTitle)
        bindViewToModel(contentView, noteModel.note.mutableContent)
    }

    private fun bindViewToModel(view: EditText, model: StringBuilder) {
        view.setText(model, TextView.BufferType.EDITABLE)
        view.addTextChangedListener(SyncingTextWatcher(model))
    }

    override fun onBackPressed() {
        bg {
            noteModel.save()
        }
        super.onBackPressed()
    }

    companion object {
        const val NOTE_ID_EXTRA = "noteId"
    }
}

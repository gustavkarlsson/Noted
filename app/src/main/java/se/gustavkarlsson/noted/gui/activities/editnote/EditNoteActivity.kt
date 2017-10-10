package se.gustavkarlsson.noted.gui.activities.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note.*
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.gui.viewmodels.note.NoteViewModel
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {

    private val component by lazy {
        NotedApplication.instance.component
            .getEditNoteActivityComponent(ActivityModule(this))
    }

    @Inject
    lateinit var noteModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        component.inject(this)
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
        noteModel.sync()
        super.onBackPressed()
    }

    companion object {
        const val NOTE_ID_EXTRA = "noteId"
    }
}

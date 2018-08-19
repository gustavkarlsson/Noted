package se.gustavkarlsson.noted.gui.activities.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.extensions.observeNonNull

class EditNoteActivity : AppCompatActivity() {

    private val activity = this

    private val viewModel: EditNoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        viewModel.bind()
        bind()
    }

    private fun EditNoteViewModel.bind() {
        title.observeNonNull(activity) { newText ->
            val oldText = titleEditText.text.toString()
            if (oldText != newText) {
                titleEditText.setText(newText)
            }
        }
        content.observeNonNull(activity) { newText ->
            val oldText = contentEditText.text.toString()
            if (oldText != newText) {
                contentEditText.setText(newText)
            }
        }
        goBack.observeNonNull(activity) {
            finish()
        }
    }

    override fun onBackPressed() {
        viewModel.stopEditingNote()
    }

    private fun bind() {
        titleEditText.textChanges()
            .subscribe(viewModel::setTitle)
        contentEditText.textChanges()
            .subscribe(viewModel::setContent)
    }
}

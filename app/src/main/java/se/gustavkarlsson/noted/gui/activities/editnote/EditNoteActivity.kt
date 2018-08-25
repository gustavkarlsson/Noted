package se.gustavkarlsson.noted.gui.activities.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.ext.android.inject
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.extensions.addTo

class EditNoteActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private val viewModel: EditNoteViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        titleEditText.setText(viewModel.initialTitle)
        contentEditText.setText(viewModel.initialContent)
        viewModel.bind()
        bind()
    }

    private fun EditNoteViewModel.bind() {
        goBack
            .subscribe { _ -> finish() }
            .addTo(disposables)
    }

    private fun bind() {
        titleEditText.textChanges()
            .subscribe(viewModel::setTitle)
            .addTo(disposables)

        contentEditText.textChanges()
            .subscribe(viewModel::setContent)
            .addTo(disposables)
    }

    override fun onBackPressed() {
        viewModel.stopEditingNote()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}

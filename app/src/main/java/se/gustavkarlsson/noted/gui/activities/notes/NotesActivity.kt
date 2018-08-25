package se.gustavkarlsson.noted.gui.activities.notes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper.VERTICAL
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_notes.*
import org.koin.android.ext.android.inject
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.extensions.addTo
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity


class NotesActivity : AppCompatActivity() {

    private val activity = this

    private var isStarted = false

    private val disposables = CompositeDisposable()

    private val viewModel: NotesViewModel by inject()

    private val noteListAdapter = NoteListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        init()
        viewModel.bind()
        bind()
    }

    override fun onStart() {
        super.onStart()
        isStarted = true
    }

    override fun onStop() {
        super.onStop()
        isStarted = false
    }

    private fun init() {
        notesRecyclerView.run {
            adapter = noteListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }
    }

    private fun NotesViewModel.bind() {
        notes.subscribe {
            noteListAdapter.notes = it
        }.addTo(disposables)

        editNote.subscribe {
            if (isStarted) {
                startActivity(Intent(activity, EditNoteActivity::class.java))
            }
        }.addTo(disposables)
    }

    private fun bind() {
        addFab.clicks()
            .subscribe { viewModel.createNewNote() }
            .addTo(disposables)

        noteListAdapter.onClick = viewModel::editNote
        noteListAdapter.onSwipe = viewModel::deleteNote
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}

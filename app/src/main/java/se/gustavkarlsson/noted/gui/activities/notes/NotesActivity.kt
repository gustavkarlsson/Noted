package se.gustavkarlsson.noted.gui.activities.notes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper.VERTICAL
import android.support.v7.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_notes.*
import org.koin.android.viewmodel.ext.android.viewModel
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.extensions.observeNonNull
import se.gustavkarlsson.noted.gui.activities.editnote.EditNoteActivity


class NotesActivity : AppCompatActivity() {

    private val activity = this

    private val viewModel: NotesViewModel by viewModel()

    private val noteListAdapter = NoteListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        notesRecyclerView.init()
        viewModel.bind()
        bind()
    }

    private fun RecyclerView.init() {
        adapter = noteListAdapter
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, VERTICAL))
    }

    private fun NotesViewModel.bind() {
        notes.observeNonNull(activity) {
            noteListAdapter.notes = it
        }
        editNote.observeNonNull(activity) {
            startActivity(Intent(activity, EditNoteActivity::class.java))
        }
    }

    private fun bind() {
        addFab.clicks()
            .subscribe { viewModel.createNewNote() }
        noteListAdapter.onClick = viewModel::editNote
        noteListAdapter.onSwipe = viewModel::deleteNote
    }
}

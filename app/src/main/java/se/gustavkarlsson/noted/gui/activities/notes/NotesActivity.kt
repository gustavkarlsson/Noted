package se.gustavkarlsson.noted.gui.activities.notes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper.VERTICAL
import kotlinx.android.synthetic.main.activity_notes.*
import org.koin.android.viewmodel.ext.android.viewModel
import se.gustavkarlsson.noted.R


class NotesActivity : AppCompatActivity() {

    val viewModel: NotesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setupNotesView()
    }

    private fun setupNotesView() {
        notesView.adapter = NoteListAdapter()
        notesView.layoutManager = LinearLayoutManager(this)
        notesView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }
}

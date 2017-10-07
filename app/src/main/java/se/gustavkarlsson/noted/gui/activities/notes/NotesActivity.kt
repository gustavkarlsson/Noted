package se.gustavkarlsson.noted.gui.activities.notes

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper.VERTICAL
import kotlinx.android.synthetic.main.activity_notes.*
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.viewmodels.notes.NotesViewModel
import javax.inject.Inject


class NotesActivity : AppCompatActivity() {

    private val component by lazy {
        NotedApplication.component
            .getNotesActivityComponent(ActivityModule(this))
    }

    @Inject
    lateinit var notesModel: NotesViewModel

    @Inject
    lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        component.inject(this)
        setupNotesView()
        bindData()
    }

    private fun setupNotesView() {
        notesView.adapter = noteListAdapter
        notesView.layoutManager = LinearLayoutManager(this)
        notesView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }

    private fun bindData() {
        notesModel.notes.observe(this, Observer<List<Note>> {
            noteListAdapter.data = it ?: emptyList()
        })
        noteListAdapter.onClick = { notesModel.open(it) }
        noteListAdapter.onSwipe = { bg { notesModel.delete(it) } }
        addButton.setOnClickListener { notesModel.openNew() }
    }
}

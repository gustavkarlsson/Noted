package se.gustavkarlsson.noted.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notes.*
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.actions.CreateNote
import se.gustavkarlsson.noted.actions.DeleteNote
import se.gustavkarlsson.noted.actions.EditNote
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.services.database.entities.Note
import javax.inject.Inject


class NotesActivity : AppCompatActivity() {

    @Inject
    lateinit var noteDao: NoteDao

    @Inject
    lateinit var createNote: CreateNote

    @Inject
    lateinit var editNote: EditNote

    @Inject
    lateinit var deleteNote: DeleteNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent.getNotesActivityComponent(ActivityModule(this)).inject(this)
        setContentView(R.layout.activity_notes)

        val viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        viewModel.load(noteDao.listAll())

        val adapter = NoteListAdapter(notesView)
        notesView.layoutManager = LinearLayoutManager(this)
        viewModel.notes.observe(this, Observer<List<Note>> {
            adapter.data = it ?: emptyList()
        })

        adapter.onClickListener = { editNote(it) }

        adapter.onSwipeListener = { deleteNote(it) }

        notesView.adapter = adapter

        addButton.setOnClickListener { createNote() }
    }
}

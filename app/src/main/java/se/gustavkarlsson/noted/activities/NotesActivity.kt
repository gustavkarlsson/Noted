package se.gustavkarlsson.noted.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notes.*
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.services.database.entities.Note
import se.gustavkarlsson.noted.services.database.NoteDao
import javax.inject.Inject


class NotesActivity : AppCompatActivity() {

    @Inject
    lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent.notesActivityComponent.inject(this)
        setContentView(R.layout.activity_notes)

        val viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        viewModel.init(noteDao)

        val adapter = NoteListAdapter(notesView)
        notesView.layoutManager = LinearLayoutManager(this)
        viewModel.notes.observe(this, Observer<List<Note>> {
            adapter.data = it ?: emptyList()
        })

        adapter.onClickListener = {
            val editNoteIntent = Intent(this, EditNoteActivity::class.java)
            editNoteIntent.putExtra("noteId", it.id)
            startActivity(editNoteIntent)
        }

        adapter.onSwipeListener = {
            viewModel.onDelete(it)
        }

        notesView.adapter = adapter

        addButton.setOnClickListener {
            startActivity(Intent(this, EditNoteActivity::class.java))
        }
    }
}

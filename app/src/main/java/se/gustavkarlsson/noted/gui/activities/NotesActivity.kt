package se.gustavkarlsson.noted.gui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notes.*
import org.jetbrains.anko.coroutines.experimental.bg
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.di.modules.ActivityModule
import se.gustavkarlsson.noted.entities.Note
import se.gustavkarlsson.noted.gui.viewmodels.notes.NotesViewModel
import javax.inject.Inject


class NotesActivity : AppCompatActivity() {

    @Inject
    lateinit var notesModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotedApplication.applicationComponent
            .getNotesActivityComponent(ActivityModule(this))
            .inject(this)
        setContentView(R.layout.activity_notes)

        val adapter = NoteListAdapter(notesView)
        notesView.adapter = adapter
        notesView.layoutManager = LinearLayoutManager(this)

        bindData(adapter)
    }

    private fun bindData(adapter: NoteListAdapter) {
        notesModel.notes.observe(this, Observer<List<Note>> {
            adapter.data = it ?: emptyList()
        })
        adapter.onClickListener = { notesModel.edit(it) }
        adapter.onSwipeListener = { bg { notesModel.delete(it) } }
        addButton.setOnClickListener { notesModel.create() }
    }
}

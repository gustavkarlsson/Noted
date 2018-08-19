package se.gustavkarlsson.noted.gui.activities.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel
import se.gustavkarlsson.noted.R

class EditNoteActivity : AppCompatActivity() {

    val viewModel: EditNoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}

package se.gustavkarlsson.noted.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import se.gustavkarlsson.noted.R
import se.gustavkarlsson.noted.extensions.addTo

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController())
        findNavController().addOnNavigatedListener(::handleStopEditingNote)
        viewModel.bind()
    }

    private fun handleStopEditingNote(navController: NavController, destination: NavDestination) {
        if (destination.id != R.id.editNoteFragment) {
            viewModel.stopEditingNote()
        }
    }

    private fun MainViewModel.bind() {
        goToEditNote.subscribe {
            findNavController().navigate(R.id.action_notesFragment_to_editNoteFragment)
        }.addTo(disposables)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController().navigateUp()

    private fun findNavController() = findNavController(R.id.mainNavHost)

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}

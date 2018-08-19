package se.gustavkarlsson.noted

import android.app.Application
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import se.gustavkarlsson.noted.krate.NoteStore
import se.gustavkarlsson.noted.krate.StreamNotes

class NotedApplication : Application() {

    private val store: NoteStore by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        store.issue(StreamNotes)
    }
}

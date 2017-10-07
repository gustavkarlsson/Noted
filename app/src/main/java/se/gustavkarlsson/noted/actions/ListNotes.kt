package se.gustavkarlsson.noted.actions

import android.arch.lifecycle.LiveData
import se.gustavkarlsson.noted.entities.Note

interface ListNotes : () -> LiveData<List<Note>>

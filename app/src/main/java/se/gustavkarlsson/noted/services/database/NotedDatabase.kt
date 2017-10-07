package se.gustavkarlsson.noted.services.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import se.gustavkarlsson.noted.services.database.entities.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NotedDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}

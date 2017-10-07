package se.gustavkarlsson.noted.services.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(DbNote::class), version = 1)
abstract class NotedDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}

package se.gustavkarlsson.noted.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbNote::class], version = 1)
abstract class NotedDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}

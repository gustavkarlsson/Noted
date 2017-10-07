package se.gustavkarlsson.noted.services.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import se.gustavkarlsson.noted.services.database.entities.Note


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note")
    fun listAll(): LiveData<List<Note>>

    @Query("SELECT * FROM Note where id = :id")
    fun findById(id: Int): Note?
}

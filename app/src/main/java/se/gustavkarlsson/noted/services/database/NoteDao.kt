package se.gustavkarlsson.noted.services.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: DbNote)

    @Update
    fun update(note: DbNote)

    @Delete
    fun delete(note: DbNote)

    @Query("SELECT * FROM Note")
    fun listAll(): LiveData<List<DbNote>>

    @Query("SELECT * FROM Note where id = :id")
    fun findById(id: Long): DbNote?
}

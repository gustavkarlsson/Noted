package se.gustavkarlsson.noted.services.database

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: DbNote)

    @Update
    fun update(note: DbNote)

    @Delete
    fun delete(note: DbNote)

    @Query("SELECT * FROM Note")
    fun listAll(): Flowable<List<DbNote>>
}

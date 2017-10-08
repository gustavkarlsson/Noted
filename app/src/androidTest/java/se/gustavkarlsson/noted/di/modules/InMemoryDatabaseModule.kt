package se.gustavkarlsson.noted.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import se.gustavkarlsson.noted.services.database.NoteDao
import se.gustavkarlsson.noted.services.database.NotedDatabase
import javax.inject.Singleton

@Module
class InMemoryDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NotedDatabase =
        Room.inMemoryDatabaseBuilder(context, NotedDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @Provides
    @Reusable
    fun provideNoteDao(database: NotedDatabase): NoteDao = database.getNoteDao()
}

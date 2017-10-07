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
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NotedDatabase =
            Room.databaseBuilder(context, NotedDatabase::class.java, "noted").build()

    @Provides
    @Reusable
    fun provideNoteDao(database: NotedDatabase): NoteDao = database.getNoteDao()
}

package se.gustavkarlsson.noted

import android.app.Application
import se.gustavkarlsson.noted.di.components.ApplicationComponent
import se.gustavkarlsson.noted.di.components.DaggerApplicationComponent
import se.gustavkarlsson.noted.di.modules.ContextModule
import se.gustavkarlsson.noted.di.modules.DatabaseModule

class NotedApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .databaseModule(DatabaseModule())
            .build()
    }

    companion object {
        lateinit var instance: NotedApplication
    }
}

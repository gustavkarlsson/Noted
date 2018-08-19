package se.gustavkarlsson.noted

import android.app.Application
import org.koin.android.ext.android.startKoin

class NotedApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}

package se.gustavkarlsson.noted.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity = activity
}

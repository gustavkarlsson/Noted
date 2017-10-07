package se.gustavkarlsson.noted.di.modules

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import se.gustavkarlsson.noted.di.scopes.PerActivity

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity = activity

    @Provides
    @PerActivity
    fun provideAppCompatActivity(): AppCompatActivity = activity

    @Provides
    @PerActivity
    fun provideFragmentActivity(): FragmentActivity = activity
}

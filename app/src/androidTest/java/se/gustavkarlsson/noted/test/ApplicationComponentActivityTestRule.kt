package se.gustavkarlsson.noted.test

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import se.gustavkarlsson.noted.NotedApplication
import se.gustavkarlsson.noted.di.components.ApplicationComponent
import kotlin.reflect.KClass

class ApplicationComponentActivityTestRule<T : Activity>(
    activityClass: KClass<T>,
    private val componentOverride: () -> ApplicationComponent
) : ActivityTestRule<T>(activityClass.java) {
    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        NotedApplication.instance.component = componentOverride()
    }
}

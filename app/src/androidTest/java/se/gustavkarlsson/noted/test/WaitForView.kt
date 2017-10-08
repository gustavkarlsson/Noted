package se.gustavkarlsson.noted.test

import android.support.test.espresso.Espresso
import android.support.test.espresso.PerformException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.util.HumanReadables
import android.support.test.espresso.util.TreeIterables
import android.view.View
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException

fun waitForView(viewMatcher: Matcher<View>, timeoutMillis: Long = 2000L) {
    Espresso.onView(ViewMatchers.isRoot()).perform(WaitForView(viewMatcher, timeoutMillis))
}

private class WaitForView(private val viewMatcher: Matcher<View>, private val timeoutMillis: Long) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isRoot()
    }

    override fun getDescription(): String {
        return "Wait for a child view $viewMatcher to appear for up to $timeoutMillis millis."
    }

    override fun perform(uiController: UiController, view: View) {
        val startTime = System.currentTimeMillis()
        val endTime = startTime + timeoutMillis
        uiController.loopMainThreadUntilIdle()
        do {
            if (contains(view)) {
                return
            }
            uiController.loopMainThreadForAtLeast(50)
        } while (System.currentTimeMillis() < endTime)
        timeout(HumanReadables.describe(view))
    }

    private fun contains(view: View): Boolean {
        return TreeIterables.breadthFirstViewTraversal(view)
            .any { viewMatcher.matches(it) }
    }

    private fun timeout(viewDescription: String?) {
        throw PerformException.Builder()
            .withActionDescription(description)
            .withViewDescription(viewDescription)
            .withCause(TimeoutException())
            .build()
    }
}

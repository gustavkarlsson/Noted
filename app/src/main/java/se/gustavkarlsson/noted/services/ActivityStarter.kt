package se.gustavkarlsson.noted.services

import android.app.Activity
import android.os.Bundle
import kotlin.reflect.KClass

interface ActivityStarter {
    fun startActivity(klass: KClass<out Activity>, extras: Map<String, Any>? = null, options: Bundle? = null)
}

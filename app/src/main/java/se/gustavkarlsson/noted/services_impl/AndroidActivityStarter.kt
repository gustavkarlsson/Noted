package se.gustavkarlsson.noted.services_impl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import se.gustavkarlsson.noted.services.ActivityStarter
import java.io.Serializable
import kotlin.reflect.KClass

class AndroidActivityStarter(private val sourceActivity: Activity) : ActivityStarter {
    override fun startActivity(klass: KClass<out Activity>, extras: Map<String, Any>?, options: Bundle?) {
        val intent = Intent(sourceActivity, klass.java)
        extras?.entries?.forEach { (name, value) ->
            intent.putAnyExtra(name, value)
        }
        sourceActivity.startActivity(intent, options)
    }

    private fun Intent.putAnyExtra(name: String, value: Any) {
        when (value) {
            is CharSequence -> putExtra(name, value)
            is Boolean -> putExtra(name, value)
            is Long -> putExtra(name, value)
            is ShortArray -> putExtra(name, value)
            is Short -> putExtra(name, value)
            is Int -> putExtra(name, value)
            is Float -> putExtra(name, value)
            is Serializable -> putExtra(name, value)
            is LongArray -> putExtra(name, value)
            is FloatArray -> putExtra(name, value)
            is IntArray -> putExtra(name, value)
            is Double -> putExtra(name, value)
            is ByteArray -> putExtra(name, value)
            is Byte -> putExtra(name, value)
            is CharArray -> putExtra(name, value)
            is String -> putExtra(name, value)
            is DoubleArray -> putExtra(name, value)
            is Char -> putExtra(name, value)
            is Parcelable -> putExtra(name, value)
            is Bundle -> putExtra(name, value)
            is BooleanArray -> putExtra(name, value)
            is Array<*> -> putArrayExtra(name, value)
            is ArrayList<*> -> putArrayListExtra(name, value)
            else -> throwInvalidTypeException(value)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun Intent.putArrayExtra(name: String, value: Array<*>) {
        when {
            value.isArrayOf<CharSequence>() -> putExtra(name, value as Array<CharSequence>)
            value.isArrayOf<String>() -> putExtra(name, value as Array<String>)
            value.isArrayOf<Parcelable>() -> putExtra(name, value as Array<Parcelable>)
            else -> throwInvalidTypeException(value)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun Intent.putArrayListExtra(name: String, value: ArrayList<*>) {
        if (value.isEmpty()) {
            putIntegerArrayListExtra(name, value as ArrayList<Int>)
        }
        val firstItem = value[0]
        when {
            Int::class.java.isInstance(firstItem) -> putIntegerArrayListExtra(name, value as ArrayList<Int>)
            String::class.java.isInstance(firstItem) -> putStringArrayListExtra(name, value as ArrayList<String>)
            CharSequence::class.java.isInstance(firstItem) -> putCharSequenceArrayListExtra(name, value as ArrayList<CharSequence>)
            Parcelable::class.java.isInstance(firstItem) -> putParcelableArrayListExtra(name, value as ArrayList<Parcelable>)
            else -> throwInvalidTypeException(value)
        }
    }

    private fun throwInvalidTypeException(value: Any) {
        throw IllegalArgumentException("Invalid extra type: ${value::class.java}")
    }
}

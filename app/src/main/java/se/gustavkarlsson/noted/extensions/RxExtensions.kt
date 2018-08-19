package se.gustavkarlsson.noted.extensions


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> LiveData<T>.toPublisher(lifecycle: LifecycleOwner): Publisher<T> =
    LiveDataReactiveStreams.toPublisher(lifecycle, this)

fun <T> Publisher<T>.toLiveData(): LiveData<T> =
    LiveDataReactiveStreams.fromPublisher(this)

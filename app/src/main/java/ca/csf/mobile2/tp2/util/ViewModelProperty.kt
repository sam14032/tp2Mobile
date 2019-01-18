package ca.csf.mobile2.tp2.util

import android.databinding.BaseObservable
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * Automatically notifies the provided BaseObservable that a property value has changed.
 *
 * See https://kotlinlang.org/docs/reference/delegated-properties.html
 */
class ViewModelProperty<T>(
    initialValue: T,
    private val observable: BaseObservable
) : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        observable.notifyChange()
    }
}
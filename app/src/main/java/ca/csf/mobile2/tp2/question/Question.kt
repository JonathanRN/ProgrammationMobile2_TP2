package ca.csf.mobile2.tp2.question

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Question @ParcelConstructor constructor(
    val id: String,
    val text: String,
    val choice1: String,
    val choice2: String,
    val nbChoice1: Int,
    val nbChoice2: Int
) /*{
    private val changeListeners: MutableList<() -> Unit> = mutableListOf()
    private val hasListeners get() = changeListeners.size > 0

    fun addChangeListener(listener: () -> Unit) {
        changeListeners.add(listener)
    }

    fun removeChangeListener(listener: () -> Unit) {
        changeListeners.remove(listener)
    }

    private fun notifyChanged() {
        if (hasListeners) {
            for (listener in changeListeners) listener()
        }
    }
}*/
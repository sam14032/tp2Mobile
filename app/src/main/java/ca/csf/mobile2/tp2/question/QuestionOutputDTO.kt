package ca.csf.mobile2.tp2.question

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import kotlin.math.roundToInt

@Parcel(Parcel.Serialization.BEAN)
data class QuestionOutputDTO @ParcelConstructor constructor(
    var choice1: String,
    var choice2: String,
    var id: String,
    var nbChoice1: Int,
    var nbChoice2: Int,
    var text: String
) {
    private fun getPercent(first: Int, second: Int): Int {
        if (first == 0 && second == 0) {
            return 0
        }

        return ((first.toFloat() / (first + second).toFloat()) * 100).roundToInt()
    }

    fun choice1Percent(): String {
        return getPercent(nbChoice1, nbChoice2).toString()
    }

    fun choice2Percent(): String {
        return getPercent(nbChoice2, nbChoice1).toString()
    }
}
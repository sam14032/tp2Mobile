package ca.csf.mobile2.tp2.question

import org.androidannotations.annotations.Bean
import org.parceler.Parcel
import org.parceler.ParcelConstructor


@Parcel(Parcel.Serialization.BEAN)
data class QuestionOutputDTO @ParcelConstructor constructor(
    var choice1 : String,
    var choice2 : String,
    var id : String,
    var nbChoice1 : Int,
    var nbChoice2 : Int,
    var text : String
)
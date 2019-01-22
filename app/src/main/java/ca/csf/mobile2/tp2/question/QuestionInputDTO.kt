package ca.csf.mobile2.tp2.question

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class QuestionInputDTO @ParcelConstructor constructor (

    var id : String,
    var text : String,
    var choice1 : String,
    var choice2 : String
)
package ca.csf.mobile2.tp2.question

import org.parceler.Parcel
import org.parceler.ParcelConstructor

//BEN_REVIEW : Voir ceci : https://en.wikipedia.org/wiki/Data_transfer_object
//
//             En gros, il vous manque une classe "Question", car vous ne devriez
//             pas utiliser vos DTO en dehors de "QuestionService".

@Parcel(Parcel.Serialization.BEAN)
data class QuestionInputDTO @ParcelConstructor constructor (

    var id : String,
    var text : String,
    var choice1 : String,
    var choice2 : String
)
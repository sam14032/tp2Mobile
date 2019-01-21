package ca.csf.mobile2.tp2.question

import android.os.Parcel
import android.os.Parcelable

class QuestionOutputDTO() : Parcelable{

    protected  var choice1 : String = ""
    protected  var choice2 : String = ""
    protected  var id : String = ""
    protected  var nbChoice1 : Int = 0
    protected  var nbChoice2 : Int = 0
    protected var text : String = ""

    constructor(parcel: Parcel) : this() {
        choice1 = parcel.readString()
        choice2 = parcel.readString()
        id = parcel.readString()
        nbChoice1 = parcel.readInt()
        nbChoice2 = parcel.readInt()
        text = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(choice1)
        parcel.writeString(choice2)
        parcel.writeString(id)
        parcel.writeInt(nbChoice1)
        parcel.writeInt(nbChoice2)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionOutputDTO> {
        override fun createFromParcel(parcel: Parcel): QuestionOutputDTO {
            return QuestionOutputDTO(parcel)
        }

        override fun newArray(size: Int): Array<QuestionOutputDTO?> {
            return arrayOfNulls(size)
        }
    }

}
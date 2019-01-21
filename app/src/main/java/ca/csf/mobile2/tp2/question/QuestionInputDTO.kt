package ca.csf.mobile2.tp2.question

import android.os.Parcel
import android.os.Parcelable

class QuestionInputDTO() : Parcelable{

    protected var id : String = ""
    protected var text : String = ""
    protected var choice1 : String = ""
    protected var choice2 : String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        text = parcel.readString()
        choice1 = parcel.readString()
        choice2 = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(text)
        parcel.writeString(choice1)
        parcel.writeString(choice2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionInputDTO> {
        override fun createFromParcel(parcel: Parcel): QuestionInputDTO {
            return QuestionInputDTO(parcel)
        }

        override fun newArray(size: Int): Array<QuestionInputDTO?> {
            return arrayOfNulls(size)
        }
    }
}
package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class QuestionActivityViewModel @ParcelConstructor constructor() :
    BaseObservable() {

    @get:Bindable
    var appState: AppState by ViewModelProperty(AppState.Fetch, this)

    @get:Bindable
    var questionOutputDTO: QuestionOutputDTO by ViewModelProperty(QuestionOutputDTO("", "", "", 1, 1, ""), this)

    @get:Bindable
    var errorCode: ErrorCode by ViewModelProperty(ErrorCode.SERVER_ERROR, this)

    //BEN_REVIEW : Bonne idée de créer ce genre de fonction pour modifier l'état de la vue, mais le nommage
    //             laisse à désirer. Ce ne sont pas des événements, alors je vois mal pourquoi cela commence
    //             par "on". Cependant, je "comprends" un peu la logique derrière, alors je vais laisser
    //             tomber.

    fun onSuccessChoice(questionOutputDTO: QuestionOutputDTO) {
        appState = AppState.MakeChoice
        this.questionOutputDTO = questionOutputDTO
    }

    fun onError(errorCode: ErrorCode) {
        appState = AppState.Error
        this.errorCode = errorCode
    }

    fun onScreenClicked(): Boolean {
        if (appState == AppState.ChoiceResult) {
            appState = AppState.Fetch
            return true
        }
        return false
    }

    fun onChoiceMade(questionOutputDTO: QuestionOutputDTO) {
        appState = AppState.ChoiceResult
        this.questionOutputDTO = questionOutputDTO
    }

    fun onRetryButtonClicked() {
        appState = AppState.Fetch
    }
}
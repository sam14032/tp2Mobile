package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.InstanceState
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class QuestionActivityViewModel @ParcelConstructor constructor() :
    BaseObservable() {

    @get:Bindable
    var appState: AppState by ViewModelProperty(AppState.Fetch, this)

    @get:Bindable
    var questionOutputDTO: QuestionOutputDTO by ViewModelProperty(QuestionOutputDTO("", "", "", 1, 1, ""), this)

    fun onResume() {

    }

    fun onPause() {

    }

    fun onSuccessChoice(questionOutputDTO: QuestionOutputDTO) {
        appState = AppState.MakeChoice
        this.questionOutputDTO = questionOutputDTO
    }

    fun onSuccessResult(questionOutputDTO: QuestionOutputDTO) {
        appState = AppState.MakeChoice
        this.questionOutputDTO = questionOutputDTO
    }

    fun onConnectivityError() {
        appState = AppState.Error
    }

    fun onServerError(errorCode: Int) {
        appState = AppState.Error
    }

    fun onScreenClicked(): Boolean{
        if(appState == AppState.ChoiceResult)
        {
            appState = AppState.Fetch
            return true
        }
        return false
    }

    fun onChoiceMade() {
         if(appState == AppState.MakeChoice) {
             appState = AppState.ChoiceResult
         }
    }

    fun onRetryButtonClicked() {
        appState = AppState.Fetch
    }
}
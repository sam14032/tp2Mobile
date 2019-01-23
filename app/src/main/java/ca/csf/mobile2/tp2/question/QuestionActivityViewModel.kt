package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.InstanceState
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class QuestionActivityViewModel @ParcelConstructor constructor() :
    BaseObservable() {

    var appState = AppState.Fetch

    fun onResume() {

    }

    fun onPause() {

    }

    fun onSuccess() {
        appState = AppState.MakeChoice
    }

    fun onConnectivityError() {
        appState = AppState.Error
    }

    fun onServerError() {
        appState = AppState.Error
    }

    fun onChoice1ButtonClicked() {
        if (appState == AppState.ChoiceResult) {
            appState = AppState.Fetch
        } else {
            appState = AppState.ChoiceResult
        }
    }

    fun onChoice2ButtonClicked() {
        if (appState == AppState.ChoiceResult) {
            appState = AppState.Fetch
        } else {
            appState = AppState.ChoiceResult
        }
    }

    fun onRetryButtonClicked() {
        appState = AppState.Fetch
    }
}
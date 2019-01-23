package ca.csf.mobile2.tp2.question

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import ca.csf.mobile2.tp2.R
import ca.csf.mobile2.tp2.databinding.ActivityQuestionBinding
import org.androidannotations.annotations.*

@SuppressLint("Registered") //Reason : Generated by Android Annotations
@DataBound
@EActivity(R.layout.activity_question)
class QuestionActivity : AppCompatActivity() {

    @Bean
    protected lateinit var questionService: QuestionService

    @InstanceState
    protected lateinit var viewModel: QuestionActivityViewModel

    protected fun onCreate(@BindingObject dataBinder: ActivityQuestionBinding) {
        if (!this::viewModel.isInitialized) {
            viewModel = QuestionActivityViewModel()
            getNewQuestion()
        }

        dataBinder.viewModel = viewModel
    }

    protected fun getNewQuestion() {
        questionService.getRandomQuestion(this::onSuccessChoice, this::onError)
    }

    protected fun sendResponse(id: String, choice: String) {
        questionService.sendChoice(id, choice, this::onSuccessResult, this::onError)
    }

    @Click(R.id.choice1_button)
    protected fun onChoice1ButtonClicked() {
        sendResponse(viewModel.questionOutputDTO.id, "choose1")
    }

    @Click(R.id.choice2_button)
    protected fun onChoice2ButtonClicked() {
        sendResponse(viewModel.questionOutputDTO.id, "choose2")
    }

    @Click(R.id.retry_button)
    protected fun onRetryButtonClicked() {
        viewModel.onRetryButtonClicked()
        getNewQuestion()
    }

    @Click(R.id.root_view)
    protected fun onScreenClicked() {
        if (viewModel.onScreenClicked()) {
            getNewQuestion()
        }
    }

    fun onSuccessChoice(questionOutputDTO: QuestionOutputDTO) {
        viewModel.onSuccessChoice(questionOutputDTO)
    }

    fun onSuccessResult(questionOutputDTO: QuestionOutputDTO) {
        viewModel.onChoiceMade(questionOutputDTO)
    }

    fun onError(errorCode: ErrorCode) {
        viewModel.onError(errorCode)
    }
}

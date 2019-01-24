package ca.csf.mobile2.tp2.question

import android.support.v7.app.AppCompatActivity
import ca.csf.mobile2.tp2.R
import ca.csf.mobile2.tp2.databinding.ActivityCreateQuestionBinding
import ca.csf.mobile2.tp2.util.ErrorType
import org.androidannotations.annotations.*

@EActivity(R.layout.activity_create_question)
@DataBound
class CreateQuestionActivity : AppCompatActivity() {

    @InstanceState
    protected lateinit var viewModel: CreateQuestionActivityViewModel

    @Bean
    protected lateinit var questionService: QuestionService

    protected fun onCreate(@BindingObject dataBinder: ActivityCreateQuestionBinding) {
        if (!this::viewModel.isInitialized)
            viewModel = CreateQuestionActivityViewModel()

        dataBinder.viewModel = viewModel
    }

    @Click(R.id.createQuestionButton)
    protected fun onCreateQuestionButtonClicked() {
        if (viewModel.isQuestionValid) {
            questionService.addQuestion(
                this::onQuestionAdd,
                this::onConnectivityError,
                this::onServerError
            )
        }
    }

    @UiThread
    protected fun onQuestionAdd(question: Question) {
        finish()
    }

    @UiThread
    protected fun onConnectivityError() {
        viewModel.error = ErrorType.Connectivity
    }

    @UiThread
    protected fun onServerError() {
        viewModel.error = ErrorType.Server
    }
}
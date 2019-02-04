package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import ca.csf.mobile2.tp2.util.ErrorType
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.parceler.Parcel
import org.parceler.ParcelConstructor

private const val DEFAULT_PERCENTAGE_IF_NO_RESULTS : String = "50%"
private const val PERCENTAGE_FORMAT : String = "%.1f%%"

@Parcel(Parcel.Serialization.BEAN)
class QuestionActivityViewModel @ParcelConstructor constructor(question: Question?) : BaseObservable() {

    var question: Question? by ViewModelProperty(question, this)

    @get:Bindable
    val text: String
        get() = question?.text ?: ""
    @get:Bindable
    val choice1: String
        get() = question?.choice1 ?: ""
    @get:Bindable
    val choice2: String
        get() = question?.choice2 ?: ""
    @get:Bindable
    val percentageChoice1: String
        get() = nbChoice1.toResultPercentage()
    @get:Bindable
    val percentageChoice2: String
        get() = nbChoice2.toResultPercentage()

    @get:Bindable
    var hasClickedChoice : Boolean by ViewModelProperty(false, this)

    @get:Bindable
    var error : ErrorType by ViewModelProperty(ErrorType.None, this)

    @get:Bindable
    var shouldShowProgressBar : Boolean by ViewModelProperty(false, this)

    private val totalResults : Float
        get() = nbChoice1 + nbChoice2

    private val nbChoice1: Float
        get() = question?.nbChoice1 ?: -1f

    private val nbChoice2: Float
        get() = question?.nbChoice2 ?: -1f

    private fun Float.toResultPercentage() : String {
        return if (totalResults > 0) PERCENTAGE_FORMAT.format((this * 100) / totalResults) else DEFAULT_PERCENTAGE_IF_NO_RESULTS
    }
}
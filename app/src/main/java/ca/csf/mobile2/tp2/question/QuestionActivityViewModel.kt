package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import kotlin.math.roundToInt

const val DEFAULT_PERCENTAGE_IF_NO_RESULTS : String = "50%"

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
        get() {
            return if (totalResults > 0) "${((nbChoice1 * 100) / totalResults).roundToInt()}%" else DEFAULT_PERCENTAGE_IF_NO_RESULTS
        }
    @get:Bindable
    val percentageChoice2: String
        get() {
            return if (totalResults > 0) "${((nbChoice2 * 100) / totalResults).roundToInt()}%" else DEFAULT_PERCENTAGE_IF_NO_RESULTS
        }

    @get:Bindable
    var hasClickedChoice : Boolean by ViewModelProperty(false, this)

    private val totalResults : Float
        get() = nbChoice1 + nbChoice2

    private val nbChoice1: Float
        get() = question?.nbChoice1 ?: -1f

    private val nbChoice2: Float
        get() = question?.nbChoice2 ?: -1f

    /*@get:Bindable
    var isServerError : Boolean? by ViewModelProperty(null, this)*/
}
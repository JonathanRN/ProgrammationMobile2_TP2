package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.parceler.Parcel
import org.parceler.ParcelConstructor

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
            return if (totalResults > 0) "${(nbChoice1 * 100) / totalResults}%" else "100%"
        }
    @get:Bindable
    val percentageChoice2: String
        get() {
            return if (totalResults > 0) "${(nbChoice2 * 100) / totalResults}%" else "100%"
        }

    @get:Bindable
    var hasClickedChoice : Boolean by ViewModelProperty(false, this)

    private val totalResults : Int
        get() = nbChoice1 + nbChoice2

    private val nbChoice1: Int
        get() = question?.nbChoice1 ?: -1

    private val nbChoice2: Int
        get() = question?.nbChoice2 ?: -1

    /*@get:Bindable
    var isServerError : Boolean? by ViewModelProperty(null, this)*/
}
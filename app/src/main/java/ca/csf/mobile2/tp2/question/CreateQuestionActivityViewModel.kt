package ca.csf.mobile2.tp2.question

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout
import ca.csf.mobile2.tp2.util.ErrorType
import ca.csf.mobile2.tp2.util.ViewModelProperty
import org.parceler.Parcel

@Parcel(Parcel.Serialization.BEAN)
class CreateQuestionActivityViewModel (
    val question: Question = Question(
        "",
        "",
        "",
        "",
        0.0f,
        0.0f
    )
) : BaseObservable() {

    @get:Bindable
    @set:Bindable
    var text: String
        get() = question.text
        set(value) {
            question.text = value
            notifyChange()
        }

    @get:Bindable
    @set:Bindable
    var choice1: String
        get() = question.choice1
        set(value) {
            question.choice1 = value
            notifyChange()
        }

    @get:Bindable
    @set:Bindable
    var choice2: String
        get() = question.choice2
        set(value) {
            question.choice2 = value
            notifyChange()
        }

    @get:Bindable
    val isTextValid
        get() = text.isNotBlank()
    @get:Bindable
    val isChoice1Valid
        get() = choice1.isNotBlank()
    @get:Bindable
    val isChoice2Valid
        get() = choice2.isNotBlank()
    @get:Bindable
    val isQuestionValid
        get() = isTextValid && isChoice1Valid && isChoice2Valid

    @get:Bindable
    var error : ErrorType by ViewModelProperty(ErrorType.None, this)
}

@BindingAdapter("errorText")
fun bindError(textInputLayout: TextInputLayout, errorText: String) {
    textInputLayout.error = errorText
}
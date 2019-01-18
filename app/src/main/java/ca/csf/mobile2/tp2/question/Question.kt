package ca.csf.mobile2.tp2.question

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Question(val id:String, val text:String,val choice1:String,val choice2:String,val nbChoice1:Int,val nbChoice2:Int):Parcelable
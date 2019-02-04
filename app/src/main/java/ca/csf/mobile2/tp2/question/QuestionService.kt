package ca.csf.mobile2.tp2.question

import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.IOException

@EBean(scope = EBean.Scope.Singleton)
class QuestionService {

    private val service: Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://m2t2.csfpwmjv.tk/")
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .build()

        service = retrofit.create(Service::class.java)
    }

    @Background
    fun getRandomQuestion(
        onSuccess: (Question) -> Unit,
        onConnectivityError: () -> Unit,
        onServerError: () -> Unit
    ) {
        try {
            val response = service.getRandomQuestion().execute()
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onServerError()
            }
        } catch (e: IOException) {
            onConnectivityError()
        }
    }

    @Background
    fun sendResult(
        id: String, choice: Int,
        onSuccess: (Question) -> Unit,
        onConnectivityError: () -> Unit,
        onServerError: () -> Unit
    ) {
        try {
            val request = if (choice == 1) service.sendResult1(id) else service.sendResult2(id)
            val response = request.execute()

            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onServerError()
            }
        } catch (e: IOException) {
            onConnectivityError()
        }
    }

    @Background
    fun addQuestion(
        question: Question, onSuccess: (Question) -> Unit,
        onConnectivityError: () -> Unit,
        onServerError: () -> Unit
    ) {
        try {
            val response = service.addQuestion(question).execute()
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onServerError()
            }
        } catch (e: IOException) {
            Log.d(QuestionService::class.simpleName, e.message)
            onConnectivityError()
        }
    }

    private interface Service {

        @GET("/api/v1/question/random/")
        fun getRandomQuestion(): Call<Question>

        @POST("/api/v1/question/{id}/choose1")
        fun sendResult1(@Path("id") id: String): Call<Question>

        @POST("/api/v1/question/{id}/choose2")
        fun sendResult2(@Path("id") id: String): Call<Question>

        @POST("/api/v1/question")
        fun addQuestion(@Body question: Question): Call<Question>
    }
}
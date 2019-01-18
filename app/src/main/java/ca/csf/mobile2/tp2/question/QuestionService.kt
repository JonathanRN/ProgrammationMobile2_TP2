package ca.csf.mobile2.tp2.question

import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.IOException

@EBean(scope = EBean.Scope.Singleton)
class QuestionService {

    private val service : Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://m2t2.csfpwmjv.tk/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
    }

    @Background
    fun getRandomQuestion(onSuccess: (Question) -> Unit,
                          onConnectivityError: () -> Unit,
                          onServerError: () -> Unit){
        try {
            val response = service.getRandomQuestion().execute()
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            }else{
                onServerError()
            }
        }catch (e:IOException)
        {
            e.printStackTrace()
            onConnectivityError()
        }

    }

    @Background
    fun sendResult(id:String,choice:Int,
                   onSuccess: (String) -> Unit,
                   onConnectivityError: () -> Unit,
                   onServerError: () -> Unit){
        try {
            val response = service.sendResult(id,choice).execute()
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            }else{
                onServerError()
            }
        }catch (e:IOException)
        {
            onConnectivityError()
        }
    }

    private interface Service {

        @GET("/api/v1/question/random")
        fun getRandomQuestion(): Call<Question>

        @POST("/api/v1/question/{id}/{choice}")
        fun sendResult(@Path("id")id:String,@Path("choice")choice:Int) : Call<String>
    }
}
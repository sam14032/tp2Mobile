package ca.csf.mobile2.tp2.question

import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.IOException

private const val QUESTION_URL = "https://m2t2.csfpwmjv.tk"
@EBean(scope = EBean.Scope.Singleton)
class QuestionService(){

    private val service : Service

    init {
        val retrofit = Retrofit.Builder()
        .baseUrl(QUESTION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(Service::class.java)
    }

    @Background
    fun getRandomQuestion(onSuccess :
                              (QuestionOutputDTO) ->Unit,
                          onConnectivityError : () ->Unit,
                          onServerError : (Int) ->Unit){
        try {
            val response = service.getRandomQuestion().execute()
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            }
            else{
                onServerError(response.code())
            }
        }
        catch (exception : IOException){
            onConnectivityError()
        }
    }

    @Background
    fun sendChoice(id : String,
                   choice: String,
                   onSuccess : (QuestionOutputDTO) ->Unit,
                   onConnectivityError : () ->Unit,
                   onServerError : (Int) ->Unit){
        try {
            val response = service.sendChoice(id,choice).execute()
            if(response.isSuccessful){
                onSuccess(response.body()!!)
            }
            else{
                onServerError(response.code())
            }
        }
        catch (exception : IOException){
            onConnectivityError()
        }
    }
}

private interface Service{
    @GET("api/v1/question/random")
    fun getRandomQuestion() : Call<QuestionOutputDTO>

    @POST("api/v1/question/{id}/{choice}")
    fun sendChoice(@Query("id") id : String,
                   @Query("choice") choice : String) : Call<QuestionOutputDTO>
}


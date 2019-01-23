package ca.csf.mobile2.tp2.question

import android.content.Context
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

private const val QUESTION_URL = "https://m2t2.csfpwmjv.tk"

@EBean(scope = EBean.Scope.Singleton)
class QuestionService(context: Context) {

    private val service: Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(QUESTION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(Service::class.java)
    }

    @Background
    fun getRandomQuestion(
        onSuccess:
            (QuestionOutputDTO) -> Unit,
        onError: (ErrorCode) -> Unit
    ) {
        try {
            val response = service.getRandomQuestion().execute()
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(ErrorCode.SERVER_ERROR)
            }
        } catch (exception: IOException) {
            onError(ErrorCode.CONNECTION_ERROR)
        }
    }

    @Background
    fun sendChoice(
        id: String,
        choice: String,
        onSuccess:
            (QuestionOutputDTO) -> Unit,
        onError: (ErrorCode) -> Unit
    ) {
        try {
            val response = service.sendChoice(id, choice).execute()
            if (response.isSuccessful) {
                onSuccess(response.body()!!)
            } else {
                onError(ErrorCode.SERVER_ERROR)
            }
        } catch (exception: IOException) {
            onError(ErrorCode.CONNECTION_ERROR)
        }
    }
}

private interface Service {
    @GET("api/v1/question/random1")
    fun getRandomQuestion(): Call<QuestionOutputDTO>

    @POST("api/v1/question/{id}/{choice}")
    fun sendChoice(
        @Path("id") id: String,
        @Path("choice") choice: String
    ): Call<QuestionOutputDTO>
}


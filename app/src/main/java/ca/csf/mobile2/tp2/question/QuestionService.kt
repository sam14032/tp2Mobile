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
//BEN_CORRECTION : Paramêtre inutilisé.
class QuestionService(context: Context) {

    private val service: Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(QUESTION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(Service::class.java)
    }

    //BEN_CORRECTION : Formattage.
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
        choice: String, //BEN_CORRECTION : Ce paramètre devrait être un entier.
        onSuccess:
            (QuestionOutputDTO) -> Unit,
        onError: (ErrorCode) -> Unit
    ) {
        //BEN_CORRECTION : Aucune validation de "choice". Seul deux valeurs devrait
        //                 être acceptées : "choose1" et "choose2". Sinon, vous aurez
        //                 une erreur 404 qui sera interprété comme une erreur de la
        //                 part du serveur, tandis que ce n'est pas le cas.

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
    //BEN_CORRECTION : Bogue ici. Il y a un "1" à la fin de cette URL. J'ai mis le code
    //                 en commentaires afin de pouvoir tester votre application un peu plus loin.
    //
    //@GET("api/v1/question/random1")
    @GET("api/v1/question/random")
    fun getRandomQuestion(): Call<QuestionOutputDTO>

    @POST("api/v1/question/{id}/{choice}")
    fun sendChoice(
        @Path("id") id: String,
        @Path("choice") choice: String
    ): Call<QuestionOutputDTO>
}


package ca.csf.mobile2.tp2.question

import org.androidannotations.annotations.EBean
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET

private const val QUESTION_URL = "https://m2t2.csfpwmjv.tk"
@EBean(scope = EBean.Scope.Singleton)
class QuestionService(){

    private val service : Service

    init {
        val retrofit = Retrofit.Builder()
        .baseUrl(QUESTION_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        service = retrofit.create(Service::class.java)
    }
}

interface Service{
    @GET("api/v1/question/random")
    fun getRandomQuestion(){

    }
}


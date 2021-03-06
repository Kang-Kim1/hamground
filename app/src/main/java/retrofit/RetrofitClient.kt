package retrofit

import android.content.res.Resources
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level


object RetrofitClient {
    private var instance : Retrofit ? = null
    private val gson = GsonBuilder().setLenient().create()

    val intercepter : HttpLoggingInterceptor = HttpLoggingInterceptor()
    val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()


    private var BASE_URL : String = "http://192.168.0.13"

    fun getInstance() : Retrofit {
        intercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(intercepter)

        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        }

        return instance!! // throws exception if value == null
    }

    fun setURL(ip : String) {
        BASE_URL = "http://$ip"
    }

    fun getURL() : String {
        return BASE_URL
    }
}

package retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var instance : Retrofit ? = null
    private val gson = GsonBuilder().setLenient().create()

    private const val BASE_URL : String = "http://192.168.1.84/"

    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return instance!! // throws exception if value == null
    }
}
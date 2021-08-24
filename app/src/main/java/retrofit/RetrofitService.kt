package retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface RetrofitService {

    // Request for LIST
    @GET("/item/select_all.php")
    fun selectAllItems() : Call<ItemResponse>
    @GET("/item/select_keyword.php")
    fun getSelecByKeyword(
        @Field("name") name: String
    ) : Call<ItemResponse>


    // Request for Board
    @GET("/board/select_all.php")
    fun selectAllPostings() : Call<PostingResponse>
}
package retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    // Request for LIST
    @GET("/item/select_all.php")
    fun selectAllItems() : Call<ItemResponse>

    @GET("/item/select_keyword.php")
    fun getSelecByKeyword(
        @Field("name") name: String
    ) : Call<ItemResponse>

    @POST("/board/post_content.php")
    fun updateBoard(
        @Body posting : Posting
    ) : Call<ResponseBody>

    // Request for Board
    @GET("/board/select_all.php")
    fun selectAllPostings() : Call<PostingResponse>
}
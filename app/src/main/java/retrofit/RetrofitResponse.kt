package retrofit

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("result")
    val result : ArrayList<Item>
)

data class Item(
    // @Serialized를 통해 데이터의 key값과 변수명이 상이해도 mapping 가능
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("link")
    val link : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("lprice")
    val lprice : Int,
    @SerializedName("hprie")
    val hprice : Int,
    @SerializedName("mall")
    val mall : String,
    @SerializedName("brand")
    val brand : String,
    @SerializedName("maker")
    val maker : String,
    @SerializedName("category1")
    val category1 : String,
    @SerializedName("category2")
    val category2 : String,
    @SerializedName("category3")
    val category3 : String,
    @SerializedName("category4")
    val category4 : String,
    )

data class PostingResponse(
    @SerializedName("result")
    val result : ArrayList<Posting>
)

data class Posting(
    @SerializedName("p_id")
    val id : String,
    @SerializedName("p_title")
    val title : String,
    @SerializedName("p_content")
    val content : String,
    @SerializedName("p_time")
    val time : String,
    @SerializedName("p_writer")
    val writer : String,
    @SerializedName("p_likes")
    val likes : String,
    @SerializedName("p_count")
    val count : String,
    @SerializedName("p_category")
    val category : String,
)

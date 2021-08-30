package retrofit

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("result")
    var result : ArrayList<Item>
)

data class Item(
    // @Serialized를 통해 데이터의 key값과 변수명이 상이해도 mapping 가능
    @SerializedName("id")
    var id : String,
    @SerializedName("name")
    var name : String,
    @SerializedName("link")
    var link : String,
    @SerializedName("image")
    var image : String,
    @SerializedName("lprice")
    var lprice : Int,
    @SerializedName("hprie")
    var hprice : Int,
    @SerializedName("mall")
    var mall : String,
    @SerializedName("brand")
    var brand : String,
    @SerializedName("maker")
    var maker : String,
    @SerializedName("category1")
    var category1 : String,
    @SerializedName("category2")
    var category2 : String,
    @SerializedName("category3")
    var category3 : String,
    @SerializedName("category4")
    var category4 : String,
)

data class PostingResponse(
    @SerializedName("result")
    var result : ArrayList<Posting>
)

data class Posting(
    @SerializedName("p_id")
    var id : String,
    @SerializedName("p_title")
    var title : String,
    @SerializedName("p_content")
    var content : String,
    @SerializedName("p_time")
    var time : String,
    @SerializedName("p_writer")
    var writer : String,
    @SerializedName("p_likes")
    var likes : String,
    @SerializedName("p_count")
    var count : String,
    @SerializedName("p_category")
    var category : String,
)

data class ReplyResponse (
    @SerializedName("result")
    var result : ArrayList<Reply>
)

data class Reply (
    @SerializedName("r_id")
    var id : String,
    @SerializedName("r_contents")
    var contents : String,
    @SerializedName("r_writer")
    var writer : String,
    @SerializedName("r_time")
    var time : String,
    @SerializedName("r_likes")
    var likes : Int,
    @SerializedName("r_for_reply")
    var forReply : Boolean,
    @SerializedName("r_to_id")
    var parentID : String

)




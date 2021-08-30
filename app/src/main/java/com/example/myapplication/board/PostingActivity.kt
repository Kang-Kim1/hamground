package com.example.myapplication.board

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import okhttp3.ResponseBody
import retrofit.Posting
import retrofit.PostingResponse
import retrofit.RetrofitClient
import retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PostingActivity : AppCompatActivity(){
    private val TAG = this::class.java.simpleName

    lateinit var postBtn : Button
    lateinit var categorySpnr : Spinner

    lateinit var board_title_et : EditText
    lateinit var board_content_et : EditText

    lateinit var retrofit : Retrofit
    lateinit var retrofitService : RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting)

        this.supportActionBar?.title = "게시글 쓰기"
        this.supportActionBar?.hide()
//        this.supportActionBar?.setBackgroundDrawable(ColorDrawable(R.attr.colorSecondary))

        board_title_et = findViewById(R.id.board_title_et)
        board_content_et = findViewById(R.id.board_content_et)
        postBtn = findViewById(R.id.board_post_btn)

        this.categorySpnr = findViewById(R.id.board_category_spnr)
        ArrayAdapter.createFromResource(
            this,
            R.array.category_spnr,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_layout)
            categorySpnr.adapter = adapter
        }


        postBtn.setOnClickListener(View.OnClickListener {
            initRetrofit()
            updateBoard(retrofitService, buildData())
        })

    }
    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun updateBoard(service : RetrofitService, posting: Posting) {
        service.updateBoard(posting).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
//                itemList = JSONParser.parseJSON(response.body())
                Log.d(TAG, "${response.raw()}")


            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
            }

        })
    }

    fun buildData() : Posting {
        var posting : Posting = Posting(
            "",
            board_title_et.text.toString(),
            board_content_et.text.toString(),
            "",
            "Writer",
            "",
            "",
            categorySpnr.selectedItem.toString()
            )

        return posting
    }
}
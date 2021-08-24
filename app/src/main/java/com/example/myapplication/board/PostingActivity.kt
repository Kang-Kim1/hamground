package com.example.myapplication.board

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class PostingActivity : AppCompatActivity(){
    lateinit var postBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting)

        this.supportActionBar?.title = "게시글 쓰기"
//        this.supportActionBar?.setBackgroundDrawable(ColorDrawable(R.attr.colorSecondary))

    }

}
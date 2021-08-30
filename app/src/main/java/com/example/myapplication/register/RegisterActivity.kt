package com.example.myapplication.register

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class RegisterActivity : AppCompatActivity(){
    private val TAG = this::class.java.simpleName

    lateinit var registerBtn : Button
    lateinit var domainSpnr : Spinner

    lateinit var board_id_et : EditText
    lateinit var board_pw_et : EditText
    lateinit var board_pw2_et : EditText

    lateinit var board_email_et : EditText
    lateinit var board_em_domain_et : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn = findViewById(R.id.register_btn)

        board_id_et = findViewById(R.id.register_id_et)
        board_pw_et = findViewById(R.id.register_pw_et)
        board_pw2_et = findViewById(R.id.register_pw2_et)

        board_email_et = findViewById(R.id.register_email_addr_et)

        this.supportActionBar?.title = "게시글 쓰기"
        this.supportActionBar?.hide()
//        this.supportActionBar?.setBackgroundDrawable(ColorDrawable(R.attr.colorSecondary))

//        searchTV.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                val input: String = searchTV.text.toString()
//                if (input != null) {
//                    search(input)
//                }
//            }
//        }
        this.domainSpnr = findViewById(R.id.register_email_domain_spnr)
        ArrayAdapter.createFromResource(
            this,
            R.array.category_spnr,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            domainSpnr.adapter = adapter
        }



    }

}
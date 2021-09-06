package com.example.myapplication.register

import android.R.color
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.myapplication.R
import com.example.myapplication.utilities.PWTransformationMethod


class RegisterActivity : AppCompatActivity(){
    private val TAG = this::class.java.simpleName

    lateinit var registerBtn : Button

    lateinit var board_id_et : EditText
    lateinit var board_nick_et : EditText
    lateinit var board_pw_et : EditText
    lateinit var board_pw2_et : EditText

    lateinit var board_email_et : EditText
    lateinit var domainSpnr : Spinner
    lateinit var board_em_domain_et : EditText

    lateinit var domainArr : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        domainArr = resources.getStringArray(R.array.domain_address)

        registerBtn = findViewById(R.id.register_btn)

        board_id_et = findViewById(R.id.register_id_et)
        board_nick_et = findViewById(R.id.register_nickname_et)
        board_pw_et = findViewById(R.id.register_pw_et)
        board_pw_et.transformationMethod = PWTransformationMethod()
        board_pw2_et = findViewById(R.id.register_pw2_et)
        board_pw2_et.transformationMethod = PWTransformationMethod()

        board_email_et = findViewById(R.id.register_email_addr_et)
        board_em_domain_et = findViewById(R.id.register_email_domain_et)
        board_em_domain_et.isEnabled = false

        this.supportActionBar?.title = "게시글 쓰기"
        this.supportActionBar?.hide()
//        this.supportActionBar?.setBackgroundDrawable(ColorDrawable(R.attr.colorSecondary))

        board_pw2_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // PW Validate
//                if(s.toString() == "") {
//                    ViewCompat.setBackgroundTintList(
//                        board_pw2_et,
//                        ColorStateList.valueOf(Color.GRAY)
//                    )
//                    return
//                }
//
//                if (s.toString() != null) {
//                    if (s.toString() != board_pw_et.text.toString()) {
//                        ViewCompat.setBackgroundTintList(
//                            board_pw2_et,
//                            ColorStateList.valueOf(Color.RED)
//                        )
//                    } else {
//                        ViewCompat.setBackgroundTintList(
//                            board_pw2_et,
//                            ColorStateList.valueOf(Color.GREEN)
//                        )
//                    }
//                 } else {
//                    ViewCompat.setBackgroundTintList(
//                        board_pw2_et,
//                        ColorStateList.valueOf(Color.DKGRAY)
//                    )
//                }
            }
        })

        this.domainSpnr = findViewById(R.id.register_email_domain_spnr)
        ArrayAdapter.createFromResource(
            this,
            R.array.domain_spnr,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            domainSpnr.adapter = adapter
        }

        domainSpnr.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //직접 입력
                if(domainArr.size == position) {
                    board_em_domain_et.isEnabled = true
                    board_em_domain_et.inputType = InputType.TYPE_CLASS_TEXT;
                    board_em_domain_et.setText("")
                } else {
                    board_em_domain_et.isEnabled = false
                    board_em_domain_et.inputType = InputType.TYPE_NULL
                    board_em_domain_et.setText(domainArr[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        registerBtn.setOnClickListener{

            // Input Validator
            if(isRegisterable()) {

            }
        }
    }
    private fun isRegisterable() : Boolean {
        var msg : String = ""
        if(board_id_et.text.toString() == null) {
            msg = "ID를 입력해주세요."
        }
        else if(board_nick_et.toString() == null) {
            msg = "별칭을 입력해주세요."
        }
        else if(board_pw_et.toString() == null) {
            msg = "비밀번호를 입력해주세요."
        }
        else if(board_pw_et.toString().length < 8) {
            msg = "비밀번호는 8글자 이상으로 설정해주세요."
        }
        else if(board_pw_et.text.toString() != board_pw2_et.text.toString()) {
            msg = "비밀번호가 일치하지 않습니다."
        } else if(board_email_et.text.toString() == null) {
            msg = "이메일 주소를 입력해주세요."
        } else if(board_em_domain_et.toString() == null) {
            msg = "이메일 도메인을 입력해주세요."
        }
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT)

        return msg == ""
    }

}
package com.example.myapplication.board

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList

class BoardFragment : Fragment() {

    private val TAG = this::class.java.simpleName

    lateinit var listAdapter : PostingAdapter
    lateinit var searchTV : EditText
    lateinit var writeBtn : Button

    lateinit var recyclerView : RecyclerView
    lateinit var postingList : ArrayList<Posting>
    lateinit var searchList : ArrayList<Posting>

    lateinit var retrofit : Retrofit
    lateinit var retrofitService : RetrofitService

    lateinit var postingIntent : Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v : View = inflater.inflate(R.layout.fragment_board, container, false)

        recyclerView = v.findViewById(R.id.board_view) as RecyclerView
        searchTV = v.findViewById(R.id.board_search_tv) as EditText
        writeBtn = v.findViewById(R.id.board_search_btn) as Button

        writeBtn.setOnClickListener(View.OnClickListener {
            postingIntent = Intent(context, PostingActivity::class.java)


            startActivity(postingIntent)

        })

        postingList = ArrayList<Posting>()
        searchList = ArrayList<Posting>()

        var llm : LinearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = llm

        listAdapter = PostingAdapter()

        recyclerView.adapter = listAdapter

        searchTV.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input : String = searchTV.text.toString()
                if(input != null){
                    search(input)
                }
            }

        })

        initRetrofit()
        getResultList(retrofitService, "")

        return v
    }
    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun getResultList(service : RetrofitService, keyword : String) {
        service.selectAllPostings().enqueue(object : Callback<PostingResponse> {
            override fun onResponse(
                call: Call<PostingResponse>,
                response: Response<PostingResponse>
            ) {
//                itemList = JSONParser.parseJSON(response.body())
                Log.d(TAG, "${response.raw()}")
                println("왔음")
                postingList = response.body()?.result as ArrayList<Posting>

                initList()
            }

            override fun onFailure(call: Call<PostingResponse>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
            }

        })
    }

    fun search(input: String) {
        searchList.clear()

        if(input.length == 0) {
            System.out.println("INPUT EMPTY!!!")
            initList()
            return
        } else {
            for(item in postingList) {
                if (item.title.lowercase(Locale.getDefault()).contains(input)) {
                    System.out.println("INPUT MATCHes!!!")
                    searchList.add(item)
                }
            }
        }
    }

    private fun initList() {
        listAdapter.setListData(postingList)
    }

    private fun setListData(resultList : ArrayList<Posting>) {
        listAdapter.setListData(resultList)
    }
}
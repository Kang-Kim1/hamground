package com.example.myapplication.list

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import retrofit.ItemResponse
import retrofit.Item
import retrofit.RetrofitClient
import retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList

class ListFragment : Fragment() {
    private val TAG = this::class.java.simpleName

    lateinit var listAdapter : ListAdapter
    lateinit var editText : EditText
    lateinit var recyclerView : RecyclerView
    lateinit var itemList : ArrayList<Item>
    lateinit var searchList : ArrayList<Item>

    lateinit var retrofit : Retrofit
    lateinit var retrofitService : RetrofitService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v : View = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = v.findViewById(R.id.list_view) as RecyclerView
        editText = v.findViewById(R.id.list_search_et) as EditText

        itemList = ArrayList<Item>()
        searchList = ArrayList<Item>()

        var llm : LinearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = llm

        listAdapter = ListAdapter()

        recyclerView.adapter = listAdapter

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input : String = editText.text.toString()
                if(input != null){
                    search(input)
                }
            }

        })

//        val hh : HttpHandler = HttpHandler()
//        var jsonRet : String = ""
//
//        try {
//            jsonRet = hh.execute("http://172.30.1.17/select_all.php", HttpHandler.SELECT_ALL).get()
//            itemList = JSONParser.parseJSON(jsonRet)
//            initList()
//            println("LIST GENERATED : " + itemList.size)
//
//        } catch(ex : Exception) {
//            ex.printStackTrace()
//        }
//        print("LIST FRAGMENT CREATE VIEW")


        initRetrofit()
        getResultList(retrofitService, "")

        return v
    }
    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun getResultList(service : RetrofitService, keyword : String) {
        service.selectAllItems().enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
//                itemList = JSONParser.parseJSON(response.body())
                Log.d(TAG, "${response.raw()}")
                println("왔음")
                itemList = response.body()?.result as ArrayList<Item>

                initList()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
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
            for(item in itemList) {
                if (item.name.lowercase(Locale.getDefault()).contains(input)) {
                    System.out.println("INPUT MATCHes!!!")
                    searchList.add(item)
                }
            }
        }
    }

    private fun initList() {
        listAdapter.setListData(itemList)
    }

    private fun setListData(resultList : ArrayList<Item>) {
        listAdapter.setListData(resultList)
    }
}
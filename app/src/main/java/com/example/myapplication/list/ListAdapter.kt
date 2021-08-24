package com.example.myapplication.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import retrofit.Item

class ListAdapter : RecyclerView.Adapter<ListViewHolder> {
    val itemList : ArrayList<Item>

    constructor() {
        itemList = ArrayList<Item>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val context = parent.context
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.item_list, parent, false)

        return ListViewHolder(context, view)
    }

    override fun onBindViewHolder(holderList: ListViewHolder, position: Int) {
        val item : Item = itemList[position]
        holderList.titleTV.text = item.name
        holderList.yearTV.text = "연도 : " + item.brand
        holderList.idTV.text = "I D : " + item.mall
        holderList.typeTV.text = "타입 : " + item.category1

        Glide.with(holderList.itemView.context).load(item.image).into(holderList.posterIV)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    fun setListData(items : ArrayList<Item>) {
        itemList.clear()
        itemList.addAll(items)

        this.notifyDataSetChanged()
    }

}
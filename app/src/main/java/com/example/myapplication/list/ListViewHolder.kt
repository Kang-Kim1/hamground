package com.example.myapplication.list

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ListViewHolder : RecyclerView.ViewHolder {
    lateinit var titleTV : TextView
    lateinit var yearTV : TextView
    lateinit var idTV : TextView
    lateinit var typeTV : TextView
    lateinit var posterIV : ImageView

    constructor(context : Context, itemView : View) : super(itemView) {
        titleTV = itemView.findViewById(R.id.tv_title)
        yearTV = itemView.findViewById(R.id.tv_mall)
        idTV = itemView.findViewById(R.id.tv_id)
        typeTV = itemView.findViewById(R.id.tv_type)
        posterIV = itemView.findViewById(R.id.iv_poster)
    }

    constructor(inflate : View) : super(inflate)

}
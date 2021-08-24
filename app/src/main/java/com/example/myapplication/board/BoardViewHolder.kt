package com.example.myapplication.board

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class BoardViewHolder : RecyclerView.ViewHolder {
    lateinit var titleTV : TextView
    lateinit var writerTV : TextView
    lateinit var countsTV : TextView
    lateinit var likesTV : TextView
    lateinit var repliesTV : TextView
    lateinit var timeTV : TextView

    constructor(context : Context, itemView : View) : super(itemView) {
        titleTV = itemView.findViewById(R.id.board_tv_title)
        writerTV = itemView.findViewById(R.id.board_tv_writer)
        countsTV = itemView.findViewById(R.id.board_tv_counts)
        likesTV = itemView.findViewById(R.id.board_tv_likes)
        repliesTV = itemView.findViewById(R.id.board_tv_replies)
        timeTV = itemView.findViewById(R.id.board_tv_time)
    }

    constructor(inflate : View) : super(inflate)

}